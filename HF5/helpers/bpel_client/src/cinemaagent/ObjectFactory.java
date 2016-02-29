package cinemaagent;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    private final static QName _ArrayOfSeat_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaAgent","ArrayOfSeat");

    public cinemaagent.ArrayOfSeat createArrayOfSeat() {
        return new cinemaagent.ArrayOfSeat();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent", name = "ArrayOfSeat")
    public JAXBElement<cinemaagent.ArrayOfSeat> createArrayOfSeat(cinemaagent.ArrayOfSeat value) {
        return new JAXBElement<cinemaagent.ArrayOfSeat>(_ArrayOfSeat_QNAME, cinemaagent.ArrayOfSeat.class, null, value);
    }

    private final static QName _Reserve_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaAgent","Reserve");

    public cinemaagent.Reserve createReserve() {
        return new cinemaagent.Reserve();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent", name = "Reserve")
    public JAXBElement<cinemaagent.Reserve> createReserve(cinemaagent.Reserve value) {
        return new JAXBElement<cinemaagent.Reserve>(_Reserve_QNAME, cinemaagent.Reserve.class, null, value);
    }

    private final static QName _ReserveResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaAgent","ReserveResponse");

    public cinemaagent.ReserveResponse createReserveResponse() {
        return new cinemaagent.ReserveResponse();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaAgent", name = "ReserveResponse")
    public JAXBElement<cinemaagent.ReserveResponse> createReserveResponse(cinemaagent.ReserveResponse value) {
        return new JAXBElement<cinemaagent.ReserveResponse>(_ReserveResponse_QNAME, cinemaagent.ReserveResponse.class, null, value);
    }
}
