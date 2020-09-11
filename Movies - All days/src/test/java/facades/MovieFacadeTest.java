package facades;

import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static Movie rm1 = new Movie(1994, "More text", new String[]{"Egon Olsen", "Benny"}, 200000);
    private static Movie rm2 = new Movie(1993, "bbb", new String[]{"Svend Olsen", "Henrik"}, 3400000);

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(rm1);
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(rm2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testGetMovieCount() {
        assertEquals(2, facade.getMovieCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetMovieByTitle() {
        List<Movie> movies = facade.getMovieByTitle("bbb");
        for (Movie mov : movies) {
            assertEquals("bbb", mov.getTitle(), "Expects the title to be correct");
        }
    }

    @Test
    public void testCreateMovie() {
        Long expectedCount = (facade.getMovieCount() + 1);
        facade.createMovie(1994, "Ny movie", new String[]{"Svend johnsen", "Benny"}, 200000);
        Long actualCount = facade.getMovieCount();
        assertEquals(expectedCount, actualCount, "Expecting to have one more movie");
    }

    @Test
    public void testGetMovieById() {
        rm1.getId();
        Movie mov = facade.getMovieById(rm1.getId());
        assertEquals(rm1.getTitle(), mov.getTitle());
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> movies = facade.getAllMovies();
        List<Movie> moviesExpected = new ArrayList();
        moviesExpected.add(rm1);
        moviesExpected.add(rm2);
        assertTrue(movies.get(0).getTitle().equals(rm1.getTitle()));
        assertTrue(movies.get(1).getTitle().equals(rm2.getTitle()));
        //for (int i = 0; i < movies.size(); i++) {
        //assertEquals(moviesExpected.get(i).getTitle(), movies.get(i).getTitle());
        //}
    }

}
