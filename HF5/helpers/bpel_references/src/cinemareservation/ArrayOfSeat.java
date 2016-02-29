package cinemareservation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSeat", propOrder = {
    "_Seat"
})
public class ArrayOfSeat {

    @XmlElement(name = "Seat", type = cinemareservation.Seat.class, nillable = true)
    protected List<cinemareservation.Seat> _Seat;

    public List<cinemareservation.Seat> getSeat() {
        if (_Seat == null) {
            _Seat = new ArrayList<cinemareservation.Seat>();
        }
        return this._Seat;
    }
}
