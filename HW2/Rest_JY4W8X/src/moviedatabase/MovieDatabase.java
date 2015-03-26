package moviedatabase;

import javax.ws.rs.core.Response;

public class MovieDatabase implements IMovieDatabase {
	
	private static MovieList movieList;

	public MovieDatabase() {
		if(movieList == null) {
			movieList = new MovieList();
		}
	}
	
	@Override
	public MovieList getAllMovies() {
		return movieList;
	}

	@Override
	public Response getMovieById(int id) {
		Movie m = movieList.getMovieById(id);
		if(m == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok(m).build();
		}
	}

	@Override
	public Result insertMovie(Movie newMovie) {
		return movieList.addMovie(newMovie);
	}

	@Override
	public void updateOrInsertMovie(int id, Movie movie) {
		movie.setId(id);
		if(movieList.getMovieById(id) == null) {
			movieList.addMovie(movie);
		} else {
			movieList.modifyMovie(movie);
		}
		
		
	}

	@Override
	public void deleteMovie(int id) {
		if(movieList.getMovieById(id) != null) {
			movieList.removeMovieById(id);
		}
	}

	@Override
	public Response getMovieIds(int year, String sortField) {
		if(sortField.equals("Director") || sortField.equals("Title")) {
			return Response.ok(movieList.getMovieIds(year, sortField)).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
