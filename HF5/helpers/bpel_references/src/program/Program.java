package program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.ws.Endpoint;

import cinemareservation.CinemaReservationService;

public class Program {

	public static void main(String[] args) {
		try {
			Program.startService(5001, 100);
			Program.startService(5002, 200);
			System.out.println("Press RETURN to close the application...");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void startService(final int port, final int price) {
		final String url = "http://localhost:"+port+"/CinemaReservation";
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Endpoint.publish(url, new CinemaReservationService(port, price));				
			}
		});
		t.start();
		System.out.println("Service is running at: "+url);
	}
	
	
}
