package jpql;

import javax.persistence.*;
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

            for (int i=0; i<1; i++){
                Member member = new Member();
                member.setUsername("DEFIAN" + i);
                member.setAge(i);
                member.chageTeam(team);
                member.setMembertype(MemberType.A);
                em.persist(member);
            }

            em.flush();
            em.clear();


            String query = "select m.team FROM Member m";
            List<Member> result = em.createQuery(query).getResultList();
            System.out.println("result.get(0) = " + result.get(0));

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
