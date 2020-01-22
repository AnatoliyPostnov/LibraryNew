package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Exceptions.FindBookByIdWasNotFoundException;
import com.postnov.library.model.Book;

import java.util.Optional;
import java.util.Set;

public interface BookService {

    public void saveBooks(Set<BookDto> booksDto);

    public BookDto findBookByBookNameAndVolume(String name, Integer volume);

    public Set<BookDto> findBooksByAuthorNameAndSurname(String name, String surname) throws FindBookByIdWasNotFoundException;

    public void deleteBookByBookNameAndVolume(String name, Integer volume);

    Set<BookDto> getBooks(Long fromBookId, Long toBookId);

    BookDto findBookById(Long Id) throws Exception;

    Optional<Long> findMaximalId();

    Long getBookIdByBookDto(BookDto bookDto);

    Book findBookByNameAndVolume(String name, Integer volume);

    void receivedBook(BookDto bookDto);
}
