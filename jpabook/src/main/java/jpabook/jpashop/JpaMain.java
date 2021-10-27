package jpabook.jpashop;

import jpabook.jpashop.domain.*;

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




            Book book = new Book();
            book.setAuthor("defianzz");

            em.persist(book);

            BookStore bookStore = new BookStore();
            bookStore.setName("defian");
            book.setBookStore(bookStore);
            em.persist(bookStore);

            System.out.println("TRANSACTION COMMIT");
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        // close
        emf.close();
    }
}
