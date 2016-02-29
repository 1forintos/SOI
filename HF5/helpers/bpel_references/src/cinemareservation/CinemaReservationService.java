package cinemareservation;

import javax.jws.WebService;

@WebService(serviceName = "CinemaReservationService", portName = "ICinemaReservation_HttpSoap11_Port", 
endpointInterface = "cinemareservation.ICinemaReservation", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", wsdlLocation = "CinemaReservation.wsdl")
public class CinemaReservationService implements ICinemaReservation {
	private int port;
	private int price;
	
	public CinemaReservationService(int port, int price) {
		this.port = port;
		this.price = price;
	}
	
    public Offer getOffer(int seatCount) {
    	System.out.println("GetOffer["+this.port+"]");
    	Offer offer = new Offer();
    	offer.setOfferId("offer_"+this.price);
    	offer.setPrice(this.price);
    	return offer;
    }

    public Reservation reserve(String offerId) {
    	System.out.println("Reserve["+this.port+"]");
    	Reservation res = new Reservation();
    	res.setReservationId("reservation_"+this.price);
    	res.setRoom("Hollywood");
    	Seat s = new Seat();
    	s.setColumn("G");
    	s.setRow("5");
    	res.getSeats().add(s);
    	return res;
    }
}
