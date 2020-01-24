package com.postnov.library.Dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReceivedBookDto {

    private LocalDate dateOfBookReceiving;

    private LocalDate dateOfBookReturn;

    @NotNull
    private BookDto book;

    @NotNull
    private LibraryCardDto libraryCard;

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public LibraryCardDto getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCardDto libraryCard) {
        this.libraryCard = libraryCard;
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

    @Override
    public String toString() {
        return "ReceivedBookDto{" +
                "book=" + book +
                ", libraryCard=" + libraryCard +
                '}';
    }
}