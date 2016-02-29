package cinemareservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation", propOrder = {
    "reservationId",
    "date",
    "room",
    "seats"
})
public class Reservation {
    @javax.xml.bind.annotation.XmlElement(name = "ReservationId", required = true, nillable = true)
    protected String reservationId;

    @javax.xml.bind.annotation.XmlElement(name = "Date", required = true, nillable = false)
    protected javax.xml.datatype.XMLGregorianCalendar date;

    @javax.xml.bind.annotation.XmlElement(name = "Room", required = true, nillable = true)
    protected String room;

    @javax.xml.bind.annotation.XmlElementWrapper(name = "Seats", required = true, nillable = true)
    @javax.xml.bind.annotation.XmlElement(name = "Seat", type = cinemareservation.Seat.class, nillable = true)
    protected java.util.List<cinemareservation.Seat> seats;

    public Reservation() {
    }

    public Reservation(String reservationId, javax.xml.datatype.XMLGregorianCalendar date, String room) {
        this.reservationId = reservationId;
        this.date = date;
        this.room = room;
    }

    public String getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(String value) {
        this.reservationId = value;
    }

    public javax.xml.datatype.XMLGregorianCalendar getDate() {
        return this.date;
    }

    public void setDate(javax.xml.datatype.XMLGregorianCalendar value) {
        this.date = value;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String value) {
        this.room = value;
    }

    public java.util.List<cinemareservation.Seat> getSeats() {
        if (this.seats == null) {
            this.seats = new java.util.ArrayList<cinemareservation.Seat>();
        }
        return this.seats;
    }
}
