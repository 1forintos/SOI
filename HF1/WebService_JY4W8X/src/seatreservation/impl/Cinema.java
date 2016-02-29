package seatreservation.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;

import seatreservation.ArrayOfSeat;
import seatreservation.CinemaException;
import seatreservation.ICinema;
import seatreservation.ICinemaBuyCinemaException;
import seatreservation.ICinemaGetAllSeatsCinemaException;
import seatreservation.ICinemaGetSeatStatusCinemaException;
import seatreservation.ICinemaInitCinemaException;
import seatreservation.ICinemaLockCinemaException;
import seatreservation.ICinemaReserveCinemaException;
import seatreservation.ICinemaUnlockCinemaException;
import seatreservation.Lock;
import seatreservation.Seat;
import seatreservation.SeatStatus;

@WebService(
	name = "Cinema",
	portName = "ICinema_HttpSoap11_Port",
	targetNamespace = "http://www.iit.bme.hu/soi/hw/SeatReservation",
	endpointInterface = "seatreservation.ICinema",
	wsdlLocation = "WEB-INF/wsdl/SeatReservation.wsdl"
)
public class Cinema implements ICinema{
	
	private static List<SeatHandler> seats;
	private static List<LockHandler> locks;
	
	private int totalRows;
	private int totalColumns;
	
	private boolean initialized = false;
	
	
	@Override
	public void init(int rows, int columns) throws ICinemaInitCinemaException {
		System.out.println("Initializing cinema with " + rows + " rows and " + columns + " columns of seats.");
		initSeats(rows, columns);
		locks = new ArrayList<LockHandler>();
		printSeats();
		initialized = true;
	}

	@Override
	public ArrayOfSeat getAllSeats() throws ICinemaGetAllSeatsCinemaException {
		if(!initialized) {
			return new ArrayOfSeat();
		}
		return getSeatsAsArrayOfSeat();
	}

	@Override
	public SeatStatus getSeatStatus(Seat seat)
			throws ICinemaGetSeatStatusCinemaException {
		if(!initialized) {
			throw new ICinemaGetSeatStatusCinemaException("Cinema is not initialized.", null);
		}
		System.out.println("Retrieving status of seat [" + seat.getRow() + "," + seat.getColumn() + "].");
		SeatHandler sh = getSeatHandler(seat);
		
		if(sh == null) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("Seat not found.");
			throw new ICinemaGetSeatStatusCinemaException(
				"Seat not found.", e
			);
		}
		return sh.getStatus();
	}

	@Override
	public String lock(Seat seat, int count) throws ICinemaLockCinemaException {
		if(!initialized) {
			throw new ICinemaLockCinemaException("Cinema is not initialized.", null);
		}
		if(count < 1) {
			throw new ICinemaLockCinemaException("Number of seats to be locked has to be greater than 0.", null);
		}
		SeatHandler sh = getSeatHandler(seat);
		if(sh == null) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("Seat not found.");
			throw new ICinemaLockCinemaException("Seat not found", e);
		}
		
		if(Integer.parseInt(sh.getSeat().getColumn()) + count - 1 > totalColumns) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("Not enough seats in the row.");
			throw new ICinemaLockCinemaException("Not enough seats in the row.", e);
		}
		
		if(!checkSeatsStatus(seat, count, SeatStatus.FREE)) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("There is at least one seat of the request that is not free.");
			throw new ICinemaLockCinemaException(
				"There is at least one seat of the request that is not free.", e
			);
		}
		System.out.println(
			"Locking " + count + " seats starting from seat [" 
			+ seat.getRow() + "," + seat.getColumn() + "]."
		);
		setSeatsStatuses(seat, count, SeatStatus.LOCKED);
		
		Lock newLock = new Lock();
		newLock.setSeat(seat);
		newLock.setCount(count);
		LockHandler newLh = new LockHandler(newLock);
		locks.add(newLh);
		System.out.println("Locking finished. Lock ID: " + newLh.getId());
		
		printSeats();
		
		return newLh.getId();
	}

	@Override
	public void unlock(String lockId) throws ICinemaUnlockCinemaException {
		if(!initialized) {
			throw new ICinemaUnlockCinemaException("Cinema is not initialized.", null);
		}
		LockHandler lh = findLock(lockId);
		if(lh == null) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("No lock found with ID: " + lockId);
			throw new ICinemaUnlockCinemaException("No lock found with ID: " + lockId, e);
		}
		if(!checkSeatsStatus(lh.getSeat(), lh.getCount(), SeatStatus.LOCKED)) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("There is at least one seat of the request that is not locked.");
			throw new ICinemaUnlockCinemaException(
				"There is at least one seat of the request that is not locked.", e
			);
		};
		System.out.println("Removing lock with ID: " + lockId);
		setSeatsStatuses(lh.getSeat(), lh.getCount(), SeatStatus.FREE);
		System.out.println("Lock removed.");
		
		printSeats();
	}

	@Override
	public void reserve(String lockId) throws ICinemaReserveCinemaException {
		if(!initialized) {
			throw new ICinemaReserveCinemaException("Cinema is not initialized.", null);
		}
		LockHandler lh = findLock(lockId);
		if(lh == null) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("No lock found with ID: " + lockId);
			throw new ICinemaReserveCinemaException("No lock found with ID: " + lockId, e);
		}
		if(!checkSeatsStatus(lh.getSeat(), lh.getCount(), SeatStatus.LOCKED)) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("There is at least one seat of the request that is not locked.");
			throw new ICinemaReserveCinemaException(
				"There is at least one seat of the request that is not locked.", e
			);
		};
		System.out.println("Reserving seats of lock with ID: " + lockId);
		setSeatsStatuses(lh.getSeat(), lh.getCount(), SeatStatus.RESERVED);
		System.out.println("Seats reserved.");
		
		printSeats();
	}

	@Override
	public void buy(String lockId) throws ICinemaBuyCinemaException {
		if(!initialized) {
			throw new ICinemaBuyCinemaException("Cinema is not initialized.", null);
		}
		LockHandler lh = findLock(lockId);
		if(lh == null) {
			CinemaException e = new CinemaException();
			e.setErrorMessage("No lock found with ID: " + lockId);
			throw new ICinemaBuyCinemaException("No lock found with ID: " + lockId, e);
		}
		System.out.println("Selling seats of lock with ID: " + lockId);
		setSeatsStatuses(lh.getSeat(), lh.getCount(), SeatStatus.SOLD);
		System.out.println("Seats sold.");
		
		printSeats();
	}
	
	private void initSeats(int rows, int columns) throws ICinemaInitCinemaException {
		seats = new ArrayList<SeatHandler>();
		if(rows < 1 || rows > 26) {
			CinemaException exception = new CinemaException();
			exception.setErrorMessage("Number of rows out of bound.");
			throw new ICinemaInitCinemaException(
				"Number of rows has to be between 1 and 26.", 
				exception				
			);
		}
		
		if(columns < 1 || columns > 100) {
			CinemaException exception = new CinemaException();
			exception.setErrorMessage("Number of columns out of bound.");
			throw new ICinemaInitCinemaException(
				"Number of columns has to be between 1 and 100.", 
				exception				
			);
		}
		
		char rowLetter = 'A';
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				Seat newSeat = new Seat();
				newSeat.setRow("" + rowLetter);
				newSeat.setColumn("" + (j + 1));
				SeatHandler sh = new SeatHandler(newSeat);
				seats.add(sh);
			}
			rowLetter++;
		}
		totalRows = rows;
		totalColumns = columns;
	}
	
	private SeatHandler getSeatHandler(Seat seat) {
		for(SeatHandler sh: seats) {
			if(seatsEqual(sh.getSeat(), seat)) {
				return sh;
			}
		}
		return null;
	}
	
	private boolean checkSeatsStatus(Seat seat, int count, SeatStatus statusToCheck) {
		Iterator<SeatHandler> si = seats.iterator();
		SeatHandler currentSeatHandler = si.next();
		while(si.hasNext() && !seatsEqual(seat, currentSeatHandler.getSeat())) {
			currentSeatHandler = si.next();
		}
		
		for(int i = 0; i < count; i++) {
			if(currentSeatHandler.getStatus() != statusToCheck) {
				return false;
			}
			if(si.hasNext()) {
				currentSeatHandler = si.next();
			}
		}
		
		return true;
	}

	private void setSeatsStatuses(Seat seat, int count, SeatStatus newStatus) {
		Iterator<SeatHandler> si = seats.iterator();
		SeatHandler currentSeatHandler = si.next();
		while(si.hasNext() && !seatsEqual(seat, currentSeatHandler.getSeat())) {
			currentSeatHandler = si.next();
		}
		
		for(int i = 0; i < count; i++) {
			System.out.println("Setting status of seat [" 
				+ currentSeatHandler.getSeat().getRow() + "," 
				+ currentSeatHandler.getSeat().getColumn() + "]"
				+ " to " + newStatus.value());
			currentSeatHandler.setStatus(newStatus);
			if(si.hasNext()) {
				currentSeatHandler = si.next();
			}
		}
	}
	
	private LockHandler findLock(String lockId) {
		for(LockHandler lh: locks) {
			if(lh.getId().equals(lockId)) {
				return lh;
			}
		}
		return null;
	}
	
	private ArrayOfSeat getSeatsAsArrayOfSeat() {
		ArrayOfSeat aos = new ArrayOfSeat();
		for(SeatHandler sh : seats) {
			aos.getSeat().add(sh.getSeat());
		}
		
		return aos;
	}
	
	private boolean seatsEqual(Seat s1, Seat s2) {
		return (
			s1.getRow().equals(s2.getRow())
			&& s1.getColumn().equals(s2.getColumn())
		);
	}
	
	private void printSeats() {
		char rowChar = 'A';
		for(int i = 0; i < totalRows; i++) {
			String rowText = "" + rowChar + ":";
			for (int j = 0; j < totalColumns; j++) {
				Seat tmpSeat = new Seat();
				tmpSeat.setRow("" + rowChar);
				tmpSeat.setColumn("" + (j + 1));
				rowText += " " + getSeatHandler(tmpSeat).getStatus().value().charAt(0);
			}
			System.out.println(rowText);
			rowChar++;
		}
		
	}
}
