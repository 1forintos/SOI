package cinemareservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reserve", propOrder = {
    "offerId"
})
public class Reserve {

    @XmlElement(name = "offerId", required = true, nillable = true)
    protected String offerId;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String value) {
        this.offerId = value;
    }
}
