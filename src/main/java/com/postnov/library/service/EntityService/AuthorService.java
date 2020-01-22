package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.AuthorDto;
import com.postnov.library.model.Author;
import com.postnov.library.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorService {

    void saveAuthors(Set<AuthorDto> authorsDto, Long book_id);

    Set<Author> findAuthorsByBook(Book book);

    List<Author> findAuthorByNameAndSurname(String name, String surname);

    void deleteAuthorByBook(Book book);

    Optional<Long> findMaximalId();
}
