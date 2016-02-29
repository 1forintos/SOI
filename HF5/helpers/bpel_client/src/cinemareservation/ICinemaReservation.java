package cinemareservation;

import javax.jws.*;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.*;

@WebService(targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ICinemaReservation {

    @WebMethod(operationName = "GetOffer", action = "http://www.iit.bme.hu/soi/hw/CinemaReservation/ICinemaReservation/GetOffer")
    @WebResult(name = "GetOfferResult", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation")
    @RequestWrapper(localName = "GetOffer", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", className = "cinemareservation.GetOffer")
    @ResponseWrapper(localName = "GetOfferResponse", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", className = "cinemareservation.GetOfferResponse")
    @Action(
        input="http://www.iit.bme.hu/soi/hw/CinemaReservation/ICinemaReservation/GetOffer",
        output="http://www.iit.bme.hu/soi/hw/CinemaReservation/ICinemaReservation/GetOfferResponse"
        )
    public cinemareservation.Offer getOffer(
        @WebParam(name = "seatCount", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation")
        int seatCount);

    @WebMethod(operationName = "Reserve", action = "http://www.iit.bme.hu/soi/hw/CinemaReservation/ICinemaReservation/Reserve")
    @WebResult(name = "ReserveResult", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation")
    @RequestWrapper(localName = "Reserve", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", className = "cinemareservation.Reserve")
    @ResponseWrapper(localName = "ReserveResponse", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", className = "cinemareservation.ReserveResponse")
    @Action(
        input="http://www.iit.bme.hu/soi/hw/CinemaReservation/ICinemaReservation/Reserve",
        output="http://www.iit.bme.hu/soi/hw/CinemaReservation/ICinemaReservation/ReserveResponse"
        )
    public cinemareservation.Reservation reserve(
        @WebParam(name = "offerId", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation")
        String offerId);
}
