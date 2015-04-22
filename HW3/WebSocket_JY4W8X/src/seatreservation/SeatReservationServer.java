package seatreservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import message.Message;
import message.MessageDecoder;
import message.MessageEncoder;

@ServerEndpoint(
	value = "/cinema",
	decoders = { MessageDecoder.class},
	encoders = { MessageEncoder.class}	
)
public class SeatReservationServer {
	
	private static MessageEncoder msgEncoder = new MessageEncoder();
	
	private static Set<Session> connections;
	private static List<Seat> seats = new ArrayList<Seat>();
	private static List<Lock> locks = new ArrayList<Lock>();
	private static int rows = 0;
	private static int columns = 0;
	
	private static boolean initialized;
	
	public SeatReservationServer() {
		if(connections == null) {
			connections = Collections.synchronizedSet(new HashSet<Session>());
		}
	}
	
	@OnOpen
	public void open(Session session) {
		System.out.println("New session opened. ID: " + session.getId());
		connections.add(session);
	}
	
	@OnClose
	public void close(Session session) {
		System.out.println("Session closed. ID: " + session.getId());
		connections.remove(session);
	}
	
	@OnError
	public void error(Session client, Throwable t) {
		System.out.println("WebSocket error occured. Error message: " + t.getMessage());
		t.printStackTrace();
	}
	
	@OnMessage
	public void message(Message message, Session client) {
		processMessage(message, client);
	}
	
	private void processMessage(Message msg, Session client) {
		switch(msg.getType()) {
			case "initRoom":
				if(msg.getProperties().containsKey("rows") 
				&& msg.getProperties().containsKey("columns")) {
					if(initializeRoom(msg, client)) {
						Message roomSizeMsg = new Message();
						roomSizeMsg.setType("roomSize");
						roomSizeMsg.addProperty("rows", rows);
						roomSizeMsg.addProperty("columns", columns);
						broadcastMessage(roomSizeMsg);
					}
				} else {
					sendErrorMessage("Message [initRoom] has to contain the following properties: [rows], [columns].", client);
				}
				break;
			case "getRoomSize": 
				sendRoomSize(client);
				break;
			case "updateSeats":
				updateSeats(client);
				break;
			case "lockSeat": 
				if(!initialized) {
					sendErrorMessage("Room is not initialized.", client);
					return;
				}
				if(msg.getProperties().containsKey("row") 
				&& msg.getProperties().containsKey("column")) {
					lockSeat(msg, client);
				} else {
					sendErrorMessage("Message [lockSeat] has to contain the following properties: [row], [column].", client);
				}
				break;
			case "unlockSeat":
				if(!initialized) {
					sendErrorMessage("Room is not initialized.", client);
					return;
				}
				if(msg.getProperties().containsKey("lockId")) {
					unlockOrReserveSeat(msg, client, SeatStatus.free);
				} else {
					sendErrorMessage("Message [unlockSeat] has to contain [lockId] property.", client);
				}
				break;
			case "reserveSeat":
				if(!initialized) {
					sendErrorMessage("Room is not initialized.", client);
					return;
				}
				if(msg.getProperties().containsKey("lockId")) {
					unlockOrReserveSeat(msg, client, SeatStatus.reserved);
				} else {
					sendErrorMessage("Message [reserveSeat] has to contain [lockId] property.", client);
				}
				break;
			default: 
				System.out.println("Unexpected message type: " + msg.getType());
				break;
		}
	}
	
	private boolean initializeRoom(Message msg, Session client) {
		int newRows = new Integer(msg.getProperties().get("rows").toString());
		int newColumns = new Integer(msg.getProperties().get("columns").toString());
		System.out.println("initRoom - [" + newRows + "] rows and [" + newColumns + "] columns");
		if(newRows < 1 || newColumns < 1) {
			String errorMsg = "Number of rows and columns have to be positive decimals.";
			sendErrorMessage(errorMsg, client);
			return false;
		}
		rows = newRows;
		columns = newColumns;
		seats = new ArrayList<Seat>();
		locks = new ArrayList<Lock>();
		initialized = true;
		for(int i = 1; i < rows + 1; i++) {
			for(int j = 1; j < columns + 1; j++) {
				seats.add(new Seat(i, j));
			}
		}
		return true;
	}

	private void sendRoomSize(Session client) {
		System.out.println("getRoomSize");
		Message roomSizeMsg = new Message();
		roomSizeMsg.setType("roomSize");
		roomSizeMsg.addProperty("rows", rows);
		roomSizeMsg.addProperty("columns", columns);
		sendMessage(roomSizeMsg, client);
	}
	
	private void updateSeats(Session client) {
		System.out.println("updateSeats");
		for(Seat s : seats) {
			Message statusMsg = new Message();
			statusMsg.setType("seatStatus");
			statusMsg.addProperty("row", s.getRow());
			statusMsg.addProperty("column", s.getColumn());
			statusMsg.addProperty("status", s.getStatus().toString());
			sendMessage(statusMsg, client);
		}
	}
	
	private void lockSeat(Message msg, Session client) {
		int row = new Integer(msg.getProperties().get("row").toString());
		int column = new Integer(msg.getProperties().get("column").toString());
		System.out.println("lockSeat - [" + row + "] row and [" + column + "] column");
		
		if(row < 1 || column < 1 || rows + 1 < row || columns + 1 < column) {
			String errMsg = "Row number has to be between 1 and " + rows 
				+ " and column has to be between 1 and " + columns;
			sendErrorMessage(errMsg, client);
			return;
		}
		
		Seat seatToLock = getSeat(row, column);
		if(seatToLock == null) {
			String errMsg = "Seat not found. Row: " + row + " Column: " + column;
			sendErrorMessage(errMsg, client);
			return;
		}
		
		if(seatToLock.getStatus() != SeatStatus.free) {
			String errMsg = "Seat at row " + row + " and column " + column + " is not free.";
			sendErrorMessage(errMsg, client);
			return;
		}
		
		if(!setSeatStatus(seatToLock, SeatStatus.locked)) {
			String errMsg = "Failed to set seat status. Row: " + seatToLock.getRow() 
				+ " Column: " + seatToLock.getColumn();
			sendErrorMessage(errMsg, client);
			return;
		}
		
		Lock newLock = new Lock(row, column);
		locks.add(newLock);
		
		Message lockResult = new Message();
		lockResult.setType("lockResult");
		lockResult.addProperty("lockId", newLock.getId());
		sendMessage(lockResult, client);

		broadcastSeatStatus(seatToLock);
	}

	private void unlockOrReserveSeat(Message msg, Session client, SeatStatus newStatus) {
		String lockId = msg.getProperties().get("lockId").toString();
		lockId = lockId.substring(1, lockId.length() - 1);
		Lock lock = getLock(lockId);
		if(lock == null ) {
			String errMsg = "Lock with ID [" + lockId + "] not found.";
			sendErrorMessage(errMsg, client);
			return;
		}
		
		Seat seat = getSeat(lock.getRow(), lock.getColumn());
		if(!setSeatStatus(seat, newStatus)) {
			String errMsg = "Failed to set seat status. Row: " + seat.getRow() 
				+ " Column: " + seat.getColumn();
			sendErrorMessage(errMsg, client);
			return;
		}
		locks.remove(lock);
		broadcastSeatStatus(seat);
	}
	
	private boolean setSeatStatus(Seat seatToLock, SeatStatus newStatus) {
		for(Seat s : seats) {
			if(seatsEqual(s, seatToLock)) {
				seatToLock.setStatus(newStatus);
				int i = seats.indexOf(s);
				seats.remove(i);
				seats.add(i, seatToLock);
				System.out.println("Changed seat status at row " + seatToLock.getRow() 
					+ ", column " + seatToLock.getColumn() + " to " + newStatus.toString()
				);
				return true;
			}
		}
		System.out.println("Failed to set seat status. Row: " + seatToLock.getRow() 
			+ " Column: " + seatToLock.getColumn()
		);
		return false;
	}

	private void sendMessage(Message msg, Session client) {
		try {
			client.getBasicRemote().sendText(msgEncoder.encode(msg));
		} catch (IOException e) {
			System.out.println("Could not send message to endpoint: " + client.getId());
			e.printStackTrace();
		} catch (EncodeException e) {
			System.out.println("Failed to encode message.");
			e.printStackTrace();
		}
	}
	
	private void sendErrorMessage(String errMsg, Session client) {
		Message msg = new Message();
		msg.setType("error");
		msg.addProperty("message", errMsg);
		sendMessage(msg, client);
	}
	
	private static Seat getSeat(int row, int column) {
		for(Seat s : seats) {
			if(s.getRow() == row && s.getColumn() == column) {
				return s;
			}
		}
		return null;
	}
	
	private static Lock getLock(String lockId) {
		for(Lock l : locks) {
			if(l.getId().equals(lockId)) {
				return l;
			}
		}
		return null;
	}
	
	private void broadcastMessage(Message msg) {
		System.out.println("Num of cons: " + connections.size());
		synchronized(connections) {
			System.out.println("Broadcasting " + msg.getType() + " message.");
			for(Session client : connections) {
				System.out.println("send msg to client: " + client.getId());
				sendMessage(msg, client);
			}
		}
	}
	
	private void broadcastSeatStatus(Seat seat) {
		Message statusMsg = new Message();
		statusMsg.setType("seatStatus");
		statusMsg.addProperty("row", seat.getRow());
		statusMsg.addProperty("column", seat.getColumn());
		statusMsg.addProperty("status", seat.getStatus().toString());
		broadcastMessage(statusMsg);
	}
	
	private boolean seatsEqual(Seat s1, Seat s2) {
		return (s1.getRow() == s2.getRow() && s1.getColumn() == s2.getColumn()) ? true : false;
	}
}
