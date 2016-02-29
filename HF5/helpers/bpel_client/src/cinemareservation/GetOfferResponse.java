package cinemareservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetOfferResponse", propOrder = {
    "getOfferResult"
})
public class GetOfferResponse {

    @XmlElement(name = "GetOfferResult", required = true, nillable = true)
    protected cinemareservation.Offer getOfferResult;

    public cinemareservation.Offer getGetOfferResult() {
        return getOfferResult;
    }

    public void setGetOfferResult(cinemareservation.Offer value) {
        this.getOfferResult = value;
    }
}
