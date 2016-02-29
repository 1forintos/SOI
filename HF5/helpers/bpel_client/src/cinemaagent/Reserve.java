package cinemaagent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reserve", propOrder = {
    "seatCount"
})
public class Reserve {

    @XmlElement(name = "seatCount", required = true, nillable = false)
    protected int seatCount;

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int value) {
        this.seatCount = value;
    }
}
