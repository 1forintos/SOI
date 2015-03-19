package moviedatabase;

import java.util.List;

public class MovieDatabase implements IMovieDatabase {
	
	private static MovieList movieList;
	
	@Override
	public List<Movie> getAllMovies() {
		if(movieList == null) {
			movieList = new MovieList();
		}
		System.out.println("תתתת");
		Movie m1 = new Movie();
		String[] actors = {"actor1", "actor2", "actor3"};
		m1.setActors(actors);
		m1.setDirector("some director" + movieList.getMovies().size());
		m1.setTitle("Title_" + movieList.getMovies().size());
		m1.setYear(2015);
		this.movieList.addMovie(m1);
		
		return movieList.getMovies();
	}

	@Override
	public Movie getMovieById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertMovie(String title, int year, String director,
			String[] actor) {
		// TODO Auto-generated method stub
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
