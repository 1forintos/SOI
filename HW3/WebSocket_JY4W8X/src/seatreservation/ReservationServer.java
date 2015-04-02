package seatreservation;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/cinema")
public class ReservationServer {
	
	
	@OnOpen
	public void open(Session session) {
		System.out.println("New session opened. ID: " + session.getId());
	}
	
	@OnClose
	public void close(Session session) {
		System.out.println("Session closed. ID: " + session.getId());
	}
	
	@OnError
	public void error(Throwable t) {
		System.out.println("WebSocket error occured. Error message: " + t.getMessage());
	}
	
	@OnMessage
	public String message(String message) {
		System.out.println("Message received: " + message);
		return "YO " + message;
	}
	
	
}
