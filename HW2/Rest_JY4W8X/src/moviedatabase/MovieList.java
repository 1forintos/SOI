package moviedatabase;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlSeeAlso(Movie.class)
public class MovieList {
	
	@XmlElement
	protected List<Movie> movies;
	
	public MovieList() {
		this.movies = new ArrayList<Movie>();
	}
	
	public List<Movie> getMovies() {
		return this.movies;
	}
	
	public void addMovie(Movie newMovie) {
		this.movies.add(newMovie);
	}
	
}
