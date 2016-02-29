package cinemareservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Seat", propOrder = {
    "row",
    "column"
})
public class Seat {
    @javax.xml.bind.annotation.XmlElement(name = "Row", required = true, nillable = true)
    protected String row;

    @javax.xml.bind.annotation.XmlElement(name = "Column", required = true, nillable = true)
    protected String column;

    public Seat() {
    }

    public Seat(String row, String column) {
        this.row = row;
        this.column = column;
    }

    public String getRow() {
        return this.row;
    }

    public void setRow(String value) {
        this.row = value;
    }

    public String getColumn() {
        return this.column;
    }

    public void setColumn(String value) {
        this.column = value;
    }
}
