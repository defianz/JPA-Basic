package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {
            Movie movie = new Movie();
            movie.setDirector("defian");
            movie.setActor("bbb");
            movie.setName("바람과하ㅏㅁ꼐 싸라지다");
            movie.setPrice(1000);

            em.persist(movie);

            em.flush();
            em.clear();

            Item movie1 = em.find(Item.class, movie.getId());
            System.out.println("movie = " + movie1.getId());

            tx.commit();
            System.out.println("TRANSACTION COMMIT");
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        // close
        emf.close();
    }
}
