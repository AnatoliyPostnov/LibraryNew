package com.postnov.library.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer volume;

    @Column
    private LocalDate dateOfPublishing;

    @Column
    private Boolean isReceivedBook;

    public Book(Long id,
                String name,
                Integer volume,
                LocalDate dateOfPublishing) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.dateOfPublishing = dateOfPublishing;
        isReceivedBook = true;
    }

    public Book(){isReceivedBook = true;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public LocalDate getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(LocalDate dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public Boolean getReceivedBook() {
        return isReceivedBook;
    }

    public void setReceivedBook(Boolean receivedBook) {
        isReceivedBook = receivedBook;
    }
}
