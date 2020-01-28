package com.postnov.library.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ReceivedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate dateOfBookReceiving = LocalDate.now();

    @Column
    private LocalDate dateOfBookReturn;

    @Column
    private Long bookId;

    @Column
    private Long libraryCardId;

    public ReceivedBook() {
    }

    public ReceivedBook(Long id,
                        Long bookId,
                        Long libraryCardId) {
        this.id = id;
        this.bookId = bookId;
        this.libraryCardId = libraryCardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfBookReceiving() {
        return dateOfBookReceiving;
    }

    public void setDateOfBookReceiving(LocalDate dateOfBookReceiving) {
        this.dateOfBookReceiving = dateOfBookReceiving;
    }

    public LocalDate getDateOfBookReturn() {
        return dateOfBookReturn;
    }

    public void setDateOfBookReturn(LocalDate dateOfBookReturn) {
        this.dateOfBookReturn = dateOfBookReturn;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getLibraryCardId() {
        return libraryCardId;
    }

    public void setLibraryCardId(Long libraryCardId) {
        this.libraryCardId = libraryCardId;
    }
}
