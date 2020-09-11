package facades;

import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getMovieCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long renameMeCount = (long) em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return renameMeCount;
        } finally {
            em.close();
        }
    }

    public List<Movie> getMovieByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery("SELECT r FROM Movie r WHERE r.title = :title", Movie.class);
            return query.setParameter("title", title).getResultList();
        } finally {
            em.close();
        }
    }

    public void populate() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(new Movie(1994, "More text", new String[]{"Egon Olsen", "Benny"}, 200000));
            em.persist(new Movie(1231, "Harry Rotter", new String[]{"Svend Svend", "Henrik"}, 212112));
            em.persist(new Movie(2666, "Rarry Hotter", new String[]{"Svend John", "Henrik"}, 2121212));
            em.persist(new Movie(2132, "Des", new String[]{"Svend Clausen", "Henrik"}, 532423));
            em.persist(new Movie(2012, "troy", new String[]{"Svend Hends", "Henrik"}, 2342332));
            em.persist(new Movie(2323, "Humans", new String[]{"Svend Mogensen", "Henrik"}, 3400000));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Movie createMovie(int year, String title, String[] actors, int cost) {
        EntityManager em = emf.createEntityManager();
        Movie mov = new Movie(year, title, actors, cost);
        try {
            em.getTransaction().begin();
            em.persist(mov);
            em.getTransaction().commit();
            return mov;
        } finally {
            em.close();
        }
    }

    public Movie getMovieById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie mov = em.find(Movie.class, id);
            return mov;
        } finally {
            em.close();
        }
    }

    public List<Movie> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery("Select e FROM Movie e", Movie.class);
            return query.getResultList();
        } finally {
            em.close();
        }

    }
}
