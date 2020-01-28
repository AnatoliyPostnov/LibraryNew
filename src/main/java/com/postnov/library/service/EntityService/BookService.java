package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Exceptions.notFoundException.FindBookByIdWasNotFoundException;
import com.postnov.library.model.Book;

import java.util.Set;

public interface BookService {

    void saveBooks(Set<BookDto> booksDto);

    void deleteBookByBook(Book book);

    void receivedBook(BookDto bookDto);

    void returnBook(Long bookId);

    BookDto getBookDtoByBookNameAndVolume(String name, Integer volume);

    BookDto getReceivedBookDtoByBookNameAndVolume(String name, Integer volume);

    BookDto getReceivedBookDtoById(Long Id);

    BookDto getBookDtoById(Long Id) throws FindBookByIdWasNotFoundException;

    BookDto makeBookDto(Book book);

    Book getBookByBookNameAndVolume(String name, Integer volume);

    Book getReceivedBookByBookNameAndVolume(String name, Integer volume);

    Book getBookById(Long Id) throws FindBookByIdWasNotFoundException;

    Book getReceivedBookById(Long Id);

    Long getBookIdByBookDto(BookDto bookDto);

    Set<BookDto> getBooksDtoByAuthorNameAndSurname(String name, String surname) throws FindBookByIdWasNotFoundException;

    Set<BookDto> getBooksDto(Long fromBookId, Long toBookId);

    Set<Long> getReceivedBooksIdByBookName(String booksName);

}
