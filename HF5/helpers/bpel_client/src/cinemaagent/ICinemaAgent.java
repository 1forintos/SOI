package cinemaagent;

import javax.jws.*;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.*;

@WebService(targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ICinemaAgent {

    @WebMethod(operationName = "Reserve", action = "http://www.iit.bme.hu/soi/hw/CinemaAgent/ICinemaAgent/Reserve")
    @WebResult(name = "ReserveResult", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent")
    @RequestWrapper(localName = "Reserve", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent", className = "cinemaagent.Reserve")
    @ResponseWrapper(localName = "ReserveResponse", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent", className = "cinemaagent.ReserveResponse")
    @Action(
        input="http://www.iit.bme.hu/soi/hw/CinemaAgent/ICinemaAgent/Reserve",
        output="http://www.iit.bme.hu/soi/hw/CinemaAgent/ICinemaAgent/ReserveResponse"
        )
    public cinemareservation.Reservation reserve(
        @WebParam(name = "seatCount", targetNamespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent")
        int seatCount);
}
