package com.postnov.library.Dto;

import javax.validation.constraints.NotNull;

public class ReceivedBookDto {

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

    @Override
    public String toString() {
        return "ReceivedBookDto{" +
                "book=" + book +
                ", libraryCard=" + libraryCard +
                '}';
    }
}