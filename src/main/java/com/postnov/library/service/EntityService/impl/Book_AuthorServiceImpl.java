package com.postnov.library.service.EntityService.impl;

import com.postnov.library.model.Book_Author;
import com.postnov.library.reposutory.Book_AuthorRepository;
import com.postnov.library.service.EntityService.Book_AuthorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class Book_AuthorServiceImpl implements Book_AuthorService {

    private final Book_AuthorRepository book_authorRepository;

    public Book_AuthorServiceImpl(Book_AuthorRepository book_authorRepository) {
        this.book_authorRepository = book_authorRepository;
    }

    @Override
    public void update(List<Long> authors_id, Long book_id) {
        for (Long author_id : authors_id){
            book_authorRepository.save(new Book_Author(book_id, author_id));
        }
    }

    @Override
    public Set<Long> findAuthorsIdByBookId(Long book_id) {
        return book_authorRepository.findAuthorsIdByBookId(book_id);
    }

    @Override
    public Optional<Long> findBooksIdByAuthorId(Long author_id) {
        return book_authorRepository.findBooksIdByAuthorId(author_id);
    }

    @Override
    public void deleteBook_AuthorByAuthor_id(Long author_id) {
        book_authorRepository.deleteBook_AuthorByAuthor_id(author_id);
    }

    @Override
    public  Optional<Long> findMaximalId() {
        return book_authorRepository.findMaximalId();
    }
}
