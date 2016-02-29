package program;

import javax.xml.ws.BindingProvider;

import cinemaagent.CinemaAgentService;
import cinemaagent.ICinemaAgent;
import cinemareservation.Reservation;

public class Program {

	public static void main(String[] args) {
		String url = "http://localhost:18080/NEPTUN/CinemaAgentService";
		CinemaAgentService cas = new CinemaAgentService();
		ICinemaAgent ca = cas.getICinemaAgentHttpSoap11Port();
		((BindingProvider)ca).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
		Reservation res = ca.reserve(5);
		System.out.println(res.getReservationId());
	}

}
