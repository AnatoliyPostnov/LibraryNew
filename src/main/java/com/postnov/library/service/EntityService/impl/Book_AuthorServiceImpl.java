package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Exceptions.notFoundException.FindBooksIdByAuthorIdWasNotFoundException;
import com.postnov.library.model.Book_Author;
import com.postnov.library.reposutory.Book_AuthorRepository;
import com.postnov.library.service.EntityService.Book_AuthorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class Book_AuthorServiceImpl implements Book_AuthorService {

    private final Book_AuthorRepository book_authorRepository;

    public Book_AuthorServiceImpl(Book_AuthorRepository book_authorRepository) {
        this.book_authorRepository = book_authorRepository;
    }

    @Override
    public void saveAuthorsIdAndBookId(List<Long> authorsId, Long bookId) {
        for (Long authorId : authorsId){
            book_authorRepository.save(new Book_Author(bookId, authorId));
        }
    }

    @Override
    public Set<Long> getAuthorsIdByBookId(Long bookId) {
        return book_authorRepository.findAuthorsIdByBookId(bookId);
    }

    @Override
    public Long getBookIdByAuthorId(Long authorId) {
        return book_authorRepository.findBooksIdByAuthorId(authorId)
                .orElseThrow(() -> new FindBooksIdByAuthorIdWasNotFoundException(authorId));
    }

    @Override
    public void deleteBook_AuthorByAuthorId(Long author_id) {
        book_authorRepository.deleteBook_AuthorByAuthor_id(author_id);
    }

}
