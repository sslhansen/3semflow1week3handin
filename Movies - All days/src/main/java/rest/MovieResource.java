package rest;

import DTO.MovieDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getFacadeExample(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";
    }

    @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populate();
        //System.out.println("--------------->"+count);
        return "added entries to the DB";
    }

    
    @Path("moviebyid/{moviebyid}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("moviebyid") long id) {
        MovieDTO movDTO = new MovieDTO(FACADE.getMovieById(id));
        return GSON.toJson(movDTO);
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<Movie> movies = FACADE.getAllMovies();
        List<MovieDTO> moviesDTO = new ArrayList();
        for (Movie mov : movies) {
            moviesDTO.add(new MovieDTO(mov));
        }
        return GSON.toJson(moviesDTO);
    }

    @Path("/title/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByTitle(@PathParam("title") String title) {
        List<Movie> movies = FACADE.getMovieByTitle(title);
        List<MovieDTO> moviesDTO = new ArrayList();
        for (Movie mov : movies) {
            moviesDTO.add(new MovieDTO(mov));
        }
        return GSON.toJson(moviesDTO);
    }

}
