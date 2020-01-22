package com.postnov.library.service.EntityService;

import com.postnov.library.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Book_AuthorService {

    void update(List<Long> authors_id, Long book_id);

    void deleteBook_AuthorByAuthor_id(Long author_id);

    Optional<Long> findBooksIdByAuthorId(Long author_id);

    Set<Long> findAuthorsIdByBookId(Long book_id);

}
