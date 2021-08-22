package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {

            Team team = new Team();
            team.setName("TEAM A");
            em.persist(team);

            for (int i=0; i<100; i++){
                Member member = new Member();
                member.setUsername("DEFIAN" + i);
                member.setAge(i);
                member.chageTeam(team);
                em.persist(member);
            }



            em.flush();
            em.clear();

            String query = "select m from Member m left join Team t on t.name = 'TEAM A'";
            List<Member> resultList = em.createQuery(query, Member.class)
                    .getResultList();

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
