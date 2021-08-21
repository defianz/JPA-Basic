package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {

            Address address = new Address("city", "street", "100");

            Member member = new Member();
            member.setUsername("Defian1");
            member.setHomeAddress(address);
            member.setWorkPeriod(new Period());
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("Defian2");
            member2.setHomeAddress(address);
            member2.setWorkPeriod(new Period());
            em.persist(member2);

            member.getHomeAddress().setCity("newCity");

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
