package moviedatabase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "movies")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Movies")
@XmlSeeAlso(Movie.class)
public class MovieList {
	
	@XmlElement(name = "movie", type = Movie.class, nillable = true)
	protected List<Movie> movies;
	
	public MovieList() {
		this.movies = new ArrayList<Movie>();
	}
	
	public List<Movie> getMovies() {
		return this.movies;
	}
	
	public Result addMovie(Movie newMovie) {
		if(!newMovie.hasId()) {
			newMovie.setId(generateId());
		}
		this.movies.add(newMovie);
		Result res = new Result();
		res.setId(newMovie.getId());
		return res;
	}

	private int generateId() {
		if(movies.size() == 0) {
			return 1;
		}
		int id = 0;
		for(Movie m : movies) {
			int currId = m.getId();
			if(currId >= id) {
				id = currId + 1;
			}
		}
		return id;
	}

	public Movie getMovieById(int id) {
		for(Movie m : movies) {
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	public void modifyMovie(Movie movie) {
		int i = 0;
		for(Movie m : movies) {
			if(m.getId() == movie.getId()) {
				movies.set(i, movie);
			}
			i++;
		}
	}

	public void removeMovieById(int id) {
		movies.remove(getMovieById(id));
	}

	public MovieIds getMovieIds(int year, String sortField) {
		MovieIds ids = new MovieIds();
		List<Movie> moviesOfYear = new ArrayList<Movie>();
		switch(sortField) {
			case "Title":
				for(Movie m : movies) {
					if(m.getYear() == year) {
						moviesOfYear.add(m);
					}
				}
				MovieTitleComparator titleComp = new MovieTitleComparator();
				moviesOfYear.sort(titleComp);
				for(Movie m : moviesOfYear) {
					ids.addId(m.getId());
				}
				break;
			case "Director":
				for(Movie m : movies) {
					if(m.getYear() == year) {
						moviesOfYear.add(m);
					}
				}
				MovieDirectorComparator directorComp = new MovieDirectorComparator();
				moviesOfYear.sort(directorComp);
				for(Movie m : moviesOfYear) {
					ids.addId(m.getId());
				}
				break;
			default: break;
		}
		return ids;
	}
	
	public class MovieTitleComparator implements Comparator<Movie>{
		 
		@Override
		public int compare(Movie m1, Movie m2) {
			return (m1.getTitle().compareTo(m2.getTitle()) >= 0) ? 1 : -1;
		}
	}
	
	public class MovieDirectorComparator implements Comparator<Movie>{
		 
		@Override
		public int compare(Movie m1, Movie m2) {
			return (m1.getTitle().compareTo(m2.getDirector()) >= 0) ? 1 : -1;
		}
	}
}
