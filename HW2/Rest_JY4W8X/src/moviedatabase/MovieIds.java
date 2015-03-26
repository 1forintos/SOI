package moviedatabase;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement(name = "movies")
public class MovieIds {
	
	@XmlElement(name = "id")
	private List<Integer> ids;
	
	public MovieIds() {
		ids = new ArrayList<Integer>();
	}
	
	public void addId(int newId) {
		ids.add(new Integer(newId));
	}
}
