package com.postnov.library.Dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class ReceivedBookDto {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBookReceiving;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivedBookDto that = (ReceivedBookDto) o;
        return Objects.equals(dateOfBookReceiving, that.dateOfBookReceiving) &&
                Objects.equals(dateOfBookReturn, that.dateOfBookReturn) &&
                Objects.equals(book, that.book) &&
                Objects.equals(libraryCard, that.libraryCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBookReceiving, dateOfBookReturn, book, libraryCard);
    }
}