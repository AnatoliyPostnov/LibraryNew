package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.AuthorDto;
import com.postnov.library.Dto.BookDto;
import com.postnov.library.Exceptions.FindAuthorByNameAndAndSurnameWasNotFoundException;
import com.postnov.library.Exceptions.FindBookByIdWasNotFoundException;
import com.postnov.library.Exceptions.FindBookByNameAndVolumeWasNotFoundException;
import com.postnov.library.Exceptions.FindBooksIdByAuthorIdWasNotFoundException;
import com.postnov.library.model.Author;
import com.postnov.library.model.Book;
import com.postnov.library.reposutory.BookRepository;
import com.postnov.library.service.EntityService.AuthorService;
import com.postnov.library.service.EntityService.BookService;
import com.postnov.library.service.EntityService.Book_AuthorService;
import com.postnov.library.service.OtherService.ConvertService;
import com.postnov.library.service.OtherService.Impl.CountIdServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final ConvertService<BookDto, Book> convertServiceBook;

    private final ConvertService<AuthorDto, Author> convertServiceAuthor;

    private final Book_AuthorService book_authorService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           ConvertService<BookDto, Book> convertServiceBook,
                           ConvertService<AuthorDto, Author> convertServiceAuthor,
                           Book_AuthorService book_authorService) {

        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.convertServiceBook = convertServiceBook;
        this.convertServiceAuthor = convertServiceAuthor;
        this.book_authorService = book_authorService;
    }

    @Override
    public void saveBooks(Set<BookDto> booksDto) {

        for (BookDto bookDto : booksDto) {

            if (!bookRepository.findBookByNameAndVolume(
                    bookDto.getName(),
                    bookDto.getVolume())
                    .isPresent()) {
                Book book = convertServiceBook.convertFromDto(bookDto, Book.class);
                book.setId(CountIdServiceImpl.Id++);
                bookRepository.save(book);
                authorService.saveAuthors(bookDto.getAuthors(), book.getId());
            }
        }
    }

    @Override
    public BookDto findBookByBookNameAndVolume(String name, Integer volume) {

        Book book = bookRepository
                .findBookByNameAndVolume(name, volume)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Book was not found")
                );

        return makeBookDto(book);
    }


    @Override
    public BookDto findBookById(Long Id) throws FindBookByIdWasNotFoundException {
        Book book = bookRepository.findBookById(Id)
                .orElseThrow(() -> new FindBookByIdWasNotFoundException(
                        "Book with id: " + Id + " was not found"
                ));
        return makeBookDto(book);
    }

    @Override
    public Optional<Long> findMaximalId() {
        return bookRepository.findMaximalId();
    }

    @Override
    public Long getBookIdByBookDto(BookDto bookDto) {
        return findBookByNameAndVolume(bookDto.getName(), bookDto.getVolume()).getId();
    }

    @Override
    public Book findBookByNameAndVolume(String name, Integer volume) {
        return bookRepository.findBookByNameAndVolume(name, volume)
                .orElseThrow(
                        () -> new FindBookByNameAndVolumeWasNotFoundException(
                                "Book with name: " + name +
                                        "volume: " + volume +
                                        "was not found")
                );
    }

    @Override
    public void receivedBook(BookDto bookDto) {
        Long Id = findBookByNameAndVolume(bookDto.getName(), bookDto.getVolume()).getId();
        bookRepository.receivedBookById(Id);
    }

    BookDto makeBookDto(Book book){
        BookDto bookDto = convertServiceBook.convertToDto(book,
                BookDto.class);

        bookDto.setAuthors(
                convertServiceAuthor.convertToSetDto(
                        authorService.findAuthorsByBook(book),
                        AuthorDto.class)
        );
        return bookDto;
    }

    @Override
    public Set<BookDto> findBooksByAuthorNameAndSurname(String name, String surname) throws FindBookByIdWasNotFoundException {

        List<Author> authors = authorService.findAuthorByNameAndSurname(name, surname);

        if (authors.isEmpty()) {
            throw new FindAuthorByNameAndAndSurnameWasNotFoundException(
                    "Author with name: " + name +
                            " surname: " + surname +
                            " was not found");
        }

        Iterator<Author> authorIterator = authors.iterator();

        Set<BookDto> booksDto = new HashSet<>();

        while (authorIterator.hasNext()) {

            Long author_id = authorIterator.next().getId();

            Long book_id = book_authorService
                    .findBooksIdByAuthorId(author_id)
                    .orElseThrow(() -> new FindBooksIdByAuthorIdWasNotFoundException(
                            "book_id with author_id: " + author_id +
                                    " was not found"

                    ));

            Book book = bookRepository
                    .findBookById(book_id)
                    .orElseThrow(
                            () -> new FindBookByIdWasNotFoundException(
                                    "Book with id: " + book_id + "was not found")
                    );

            booksDto.add(findBookByBookNameAndVolume(book.getName(), book.getVolume()));

        }

        return booksDto;
    }

    @Override
    public void deleteBookByBookNameAndVolume(String name, Integer volume) {
        Book book = findBookByNameAndVolume(name, volume);
        authorService.deleteAuthorByBook(book);
        bookRepository.deleteBookByNameAndVolume(name, volume);
    }

    @Override
    public Set<BookDto> getBooks(Long fromBookId, Long toBookId) {
        Set<BookDto> booksDto = new HashSet<>();
        for(Long i = fromBookId; i <= toBookId; ++i){
            try{
            booksDto.add(findBookById(i));
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }
        return booksDto;
    }

}
