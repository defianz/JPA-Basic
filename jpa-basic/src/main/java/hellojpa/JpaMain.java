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


            Team team = new Team();
            team.setName("TEAMA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Defian");
//            member.changeTeam(team);
            em.persist(member);

//            team.getMembers().add(member);

//            em.flush();
//            em.clear();

            team.addMember(member);

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            
            for (Member m : members){
                System.out.println("m.getUsername() = " + m.getUsername());
            }


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
