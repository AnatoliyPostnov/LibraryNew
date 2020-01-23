package com.postnov.library.service.EntityService;

import com.postnov.library.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Book_AuthorService {

    void saveAuthorsIdAndBookId(List<Long> authors_id, Long book_id);

    void deleteBook_AuthorByAuthor_id(Long author_id);

    Long getBookIdByAuthorId(Long author_id);

    Set<Long> getAuthorsIdByBookId(Long book_id);

}
