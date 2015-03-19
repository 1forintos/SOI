package moviedatabase;

import java.util.List;

public class MovieDatabase implements IMovieDatabase {
	
	private static MovieList movieList;

	public MovieDatabase() {
		if(movieList == null) {
			movieList = new MovieList();
		}
	}
	
	
	@Override
	public List<Movie> getAllMovies() {
		return movieList.getMovies();
	}

	@Override
	public Movie getMovieById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertMovie(Movie newMovie) {
		movieList.addMovie(newMovie);
		return null;
	}

	@Override
	public void updateOrInsertMovie(int id, String title, int year,
			String director, String[] actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMovie(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getMovieIdsByField(int year, String field) {
		// TODO Auto-generated method stub
		return null;
	}

}
