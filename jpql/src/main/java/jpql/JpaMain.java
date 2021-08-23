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
            team.setName("팀A");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("팀B");
            em.persist(team2);

            makeMember("회원1",team,em);
            makeMember("회원2",team,em);
            makeMember("회원3",team2,em);
            makeMember("회원4",null,em);


            em.flush();
            em.clear();
            System.out.println("==============================");


            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            em.clear();
            System.out.println("resultCount = " + resultCount);


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


    private static Member makeMember(String name, Team team, EntityManager em){
        Member member = new Member();
        member.setUsername(name);
        if(team != null){
            member.chageTeam(team);
        }
        em.persist(member);
        return member;
    }

}
