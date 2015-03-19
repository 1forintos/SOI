package moviedatabase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class Movie {
	
	@XmlElement
	private String title;
	
	@XmlElement
	private int year;
	
	@XmlElement
	private String director;
	
	@XmlElement
	private String[] actor; // TODO maybe arraylist
	
	private String id;
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setYear(int newYear) {
		this.year = newYear;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void setDirector(String newDirector) {
		this.director = newDirector;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	public void setActors(String[] newActors) {
		this.actor = newActors;
	}
	
	public String[] getActors() {
		return this.actor;
	}
	
	public void setId(String newId) {
		this.id = newId;
	}
	
	public String getId() {
		return this.id;
	}
}
