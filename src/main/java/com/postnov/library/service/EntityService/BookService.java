package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Exceptions.FindBookByIdWasNotFoundException;
import com.postnov.library.model.Book;

import java.util.Set;

public interface BookService {

    void saveBooks(Set<BookDto> booksDto);

    void deleteBookByBookNameAndVolume(String name, Integer volume);

    void receivedBook(BookDto bookDto);

    void returnBook(Long bookId);

    BookDto findBookByBookNameAndVolume(String name, Integer volume);

    BookDto findReceivedBookByBookNameAndVolume(String name, Integer volume);

    BookDto findBookById(Long Id) throws FindBookByIdWasNotFoundException;

    Book findBookByNameAndVolume(String name, Integer volume);

    Long getBookIdByBookDto(BookDto bookDto);

    Set<BookDto> findBooksByAuthorNameAndSurname(String name, String surname) throws FindBookByIdWasNotFoundException;

    Set<BookDto> getBooks(Long fromBookId, Long toBookId);

    Set<Long> findBooksIdByBooksName(String booksName);
}
