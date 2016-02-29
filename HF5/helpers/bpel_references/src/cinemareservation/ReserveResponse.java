package cinemareservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReserveResponse", propOrder = {
    "reserveResult"
})
public class ReserveResponse {

    @XmlElement(name = "ReserveResult", required = true, nillable = true)
    protected cinemareservation.Reservation reserveResult;

    public cinemareservation.Reservation getReserveResult() {
        return reserveResult;
    }

    public void setReserveResult(cinemareservation.Reservation value) {
        this.reserveResult = value;
    }
}
