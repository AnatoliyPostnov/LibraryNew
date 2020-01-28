package com.postnov.library.model;

import javax.persistence.*;

@Entity
public class Book_Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private Long book_id;

    @Column
    private Long author_id;

    public Book_Author() {
    }

    public Book_Author(Long book_id, Long author_id) {
        this.book_id = book_id;
        this.author_id = author_id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }
}
