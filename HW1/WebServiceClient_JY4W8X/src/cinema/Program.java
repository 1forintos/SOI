package cinema;

import javax.xml.ws.BindingProvider;

import seatreservation.CinemaService;
import seatreservation.ICinema;
import seatreservation.ICinemaBuyCinemaException;
import seatreservation.ICinemaLockCinemaException;
import seatreservation.ICinemaReserveCinemaException;
import seatreservation.Seat;

public class Program {
	public static void main(String[] args) {
		String url = args[0];
		String row = args[1];
		String column = args[2];
		String task = args[3];
		
		CinemaService factory = new CinemaService();
		ICinema client = factory.getICinemaHttpSoap11Port();
		BindingProvider bp = (BindingProvider) client;
		bp.getRequestContext().put(
			BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
			url
		);
		try {
			String lockId = "";
			Seat seat = new Seat();
			seat.setRow(row);
			seat.setColumn(column);
			switch(task) {
				case "Lock": 
					lockId = client.lock(seat, 1);
					break;
				case "Reserve": 
					lockId = client.lock(seat, 1);
					client.reserve(lockId);
					break;
				case "Buy": 
					lockId = client.lock(seat, 1);
					client.buy(lockId);
					break;
				default: break;
			}
			System.out.println("Success.");
		} catch (ICinemaLockCinemaException e) {
			System.out.println("Error: " + e.getMessage());
			//e.printStackTrace();
		} catch (ICinemaReserveCinemaException e) {
			System.out.println("Error: " + e.getMessage());
			//e.printStackTrace();
		} catch (ICinemaBuyCinemaException e) {
			System.out.println("Error: " + e.getMessage());
			//e.printStackTrace();
		}
		
	}
}

