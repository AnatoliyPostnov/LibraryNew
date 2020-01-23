package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.AuthorDto;
import com.postnov.library.Exceptions.FindAuthorByIdWasNotFoundException;
import com.postnov.library.Exceptions.FindAuthorByNameAndAndSurnameWasNotFoundException;
import com.postnov.library.model.Author;
import com.postnov.library.model.Book;
import com.postnov.library.reposutory.AuthorRepository;
import com.postnov.library.service.EntityService.AuthorService;
import com.postnov.library.service.EntityService.Book_AuthorService;
import com.postnov.library.service.OtherService.ConvertService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final ConvertService<AuthorDto, Author> convertService;

    private final Book_AuthorService book_authorService;

    public AuthorServiceImpl(
            AuthorRepository authorRepository,
            ConvertService<AuthorDto, Author> convertService,
            Book_AuthorService book_authorService) {
        this.authorRepository = authorRepository;
        this.convertService = convertService;
        this.book_authorService = book_authorService;
    }

    @Override
    public void saveAuthors(Set<AuthorDto> authors, Long book_id) {

        List<Long> authors_id = new ArrayList<>();

        for(AuthorDto authorDto : authors){
            Author author = convertService.convertFromDto(authorDto, Author.class);;
            authors_id.add(authorRepository.save(author).getId());
        }
        book_authorService.saveAuthorsIdAndBookId(authors_id, book_id);
    }

    @Override
    public void deleteAuthorByBook(Book book) {
        for (Author author : getAuthorsByBook(book)){
            book_authorService.deleteBook_AuthorByAuthor_id(author.getId());
            authorRepository.deleteAuthorById(author.getId());
        }
    }

    @Override
    public Set<Author> getAuthorsByBook(Book book) {

        Set<Long> authors_id = book_authorService.getAuthorsIdByBookId(book.getId());

        Set<Author> authors = new HashSet<>();

        for (Long author_id : authors_id){
            authors.add(authorRepository.findAuthorById(author_id)
                    .orElseThrow(() -> new FindAuthorByIdWasNotFoundException(
                            "Author with" + author_id + "was not found"
                    )));
        }

        return authors;
    }

    @Override
    public List<Author> getAuthorsByNameAndSurname(String name, String surname) {

        List<Author> authors = authorRepository.findAuthorByNameAndSurname(name, surname);

        if (authors.isEmpty()) {
            throw new FindAuthorByNameAndAndSurnameWasNotFoundException(
                    "Author with name: " + name +
                            " surname: " + surname +
                            " was not found");
        }
        return authors;
    }

}
