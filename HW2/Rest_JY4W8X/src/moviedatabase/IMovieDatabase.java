package moviedatabase;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("MovieDatabase")
public interface IMovieDatabase {

	@GET
	@Path("movies")
	@Consumes({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	public List<Movie> getAllMovies();
	
	@GET
	@Path("movies/{id}")
	@Consumes({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	public Movie getMovieById(@PathParam("id") int id);
	
	@POST
	@Path("movies")
	@Consumes({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	public String insertMovie(Movie movie);
	
	@PUT
	@Path("movies/{id}")
	@Consumes({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	public void updateOrInsertMovie(
		@PathParam("id") int id,
		@FormParam("title") String title,
		@FormParam("year") int year,
		@FormParam("director") String director,
		@FormParam("actor") String[] actor
	);
	
	@DELETE
	@Path("movies/{id}")
	@Consumes({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	public void deleteMovie(@PathParam("id") String id);
	
	@GET
	@Path("movies/find")
	@Consumes({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	@Produces({
		MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON
	})
	public List<String> getMovieIdsByField(
		@QueryParam("year") int year,
		@QueryParam("field") String field
	);
}
