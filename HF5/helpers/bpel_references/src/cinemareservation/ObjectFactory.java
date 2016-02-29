package cinemareservation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    private final static QName _Offer_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaReservation","Offer");

    public cinemareservation.Offer createOffer() {
        return new cinemareservation.Offer();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", name = "Offer")
    public JAXBElement<cinemareservation.Offer> createOffer(cinemareservation.Offer value) {
        return new JAXBElement<cinemareservation.Offer>(_Offer_QNAME, cinemareservation.Offer.class, null, value);
    }

    private final static QName _Seat_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaReservation","Seat");

    public cinemareservation.Seat createSeat() {
        return new cinemareservation.Seat();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", name = "Seat")
    public JAXBElement<cinemareservation.Seat> createSeat(cinemareservation.Seat value) {
        return new JAXBElement<cinemareservation.Seat>(_Seat_QNAME, cinemareservation.Seat.class, null, value);
    }

    private final static QName _Reservation_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaReservation","Reservation");

    public cinemareservation.Reservation createReservation() {
        return new cinemareservation.Reservation();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", name = "Reservation")
    public JAXBElement<cinemareservation.Reservation> createReservation(cinemareservation.Reservation value) {
        return new JAXBElement<cinemareservation.Reservation>(_Reservation_QNAME, cinemareservation.Reservation.class, null, value);
    }

    private final static QName _ArrayOfSeat_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaReservation","ArrayOfSeat");

    public cinemareservation.ArrayOfSeat createArrayOfSeat() {
        return new cinemareservation.ArrayOfSeat();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", name = "ArrayOfSeat")
    public JAXBElement<cinemareservation.ArrayOfSeat> createArrayOfSeat(cinemareservation.ArrayOfSeat value) {
        return new JAXBElement<cinemareservation.ArrayOfSeat>(_ArrayOfSeat_QNAME, cinemareservation.ArrayOfSeat.class, null, value);
    }

    private final static QName _GetOffer_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaReservation","GetOffer");

    public cinemareservation.GetOffer createGetOffer() {
        return new cinemareservation.GetOffer();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", name = "GetOffer")
    public JAXBElement<cinemareservation.GetOffer> createGetOffer(cinemareservation.GetOffer value) {
        return new JAXBElement<cinemareservation.GetOffer>(_GetOffer_QNAME, cinemareservation.GetOffer.class, null, value);
    }

    private final static QName _GetOfferResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaReservation","GetOfferResponse");

    public cinemareservation.GetOfferResponse createGetOfferResponse() {
        return new cinemareservation.GetOfferResponse();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", name = "GetOfferResponse")
    public JAXBElement<cinemareservation.GetOfferResponse> createGetOfferResponse(cinemareservation.GetOfferResponse value) {
        return new JAXBElement<cinemareservation.GetOfferResponse>(_GetOfferResponse_QNAME, cinemareservation.GetOfferResponse.class, null, value);
    }

    private final static QName _Reserve_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaReservation","Reserve");

    public cinemareservation.Reserve createReserve() {
        return new cinemareservation.Reserve();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", name = "Reserve")
    public JAXBElement<cinemareservation.Reserve> createReserve(cinemareservation.Reserve value) {
        return new JAXBElement<cinemareservation.Reserve>(_Reserve_QNAME, cinemareservation.Reserve.class, null, value);
    }

    private final static QName _ReserveResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/CinemaReservation","ReserveResponse");

    public cinemareservation.ReserveResponse createReserveResponse() {
        return new cinemareservation.ReserveResponse();
    }

    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/CinemaReservation", name = "ReserveResponse")
    public JAXBElement<cinemareservation.ReserveResponse> createReserveResponse(cinemareservation.ReserveResponse value) {
        return new JAXBElement<cinemareservation.ReserveResponse>(_ReserveResponse_QNAME, cinemareservation.ReserveResponse.class, null, value);
    }
}
