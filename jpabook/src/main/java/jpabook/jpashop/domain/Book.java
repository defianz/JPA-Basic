package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String author;

    private String isbn;


    @ManyToOne
    private BookStore bookStore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookStore getBookStore() {
        return bookStore;
    }

    public void setBookStore(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
