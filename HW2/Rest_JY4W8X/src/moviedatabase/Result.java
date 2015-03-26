package moviedatabase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
public class Result {
	
	@XmlElement
	private int id;
	
	public void setId(int i) {
		this.id = i;
	}
	
	public int getId() {
		return this.id;
	}
}
