package moviedatabase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
public class Movie {
	
	@XmlElement
	private String title;
	
	@XmlElement
	private int year;
	
	@XmlElement
	private String director;
	
	@XmlElement
	private String[] actor;
	
	@XmlTransient
	private int id;
	
	@XmlTransient
	private boolean hasId = false;
	
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
	
	public void setId(int newId) {
		this.hasId = true;
		this.id = newId;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean hasId() {
		return this.hasId;
	}
}
