package cinemareservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Offer", propOrder = {
    "offerId",
    "price"
})
public class Offer {
    @javax.xml.bind.annotation.XmlElement(name = "OfferId", required = true, nillable = true)
    protected String offerId;

    @javax.xml.bind.annotation.XmlElement(name = "Price", required = true, nillable = false)
    protected int price;

    public Offer() {
    }

    public Offer(String offerId, int price) {
        this.offerId = offerId;
        this.price = price;
    }

    public String getOfferId() {
        return this.offerId;
    }

    public void setOfferId(String value) {
        this.offerId = value;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int value) {
        this.price = value;
    }
}
