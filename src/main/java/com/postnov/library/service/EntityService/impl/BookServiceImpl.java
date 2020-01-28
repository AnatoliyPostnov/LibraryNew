package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.AuthorDto;
import com.postnov.library.Dto.BookDto;
import com.postnov.library.Exceptions.notFoundException.FindBookByIdWasNotFoundException;
import com.postnov.library.Exceptions.notFoundException.FindReceivedBookByIdWasNotFoundException;
import com.postnov.library.Exceptions.notFoundException.FindReceivedBookByNameAndVolumeWasNotFoundException;
import com.postnov.library.Exceptions.notFoundException.FindReturnBookByNameAndVolumeWasNotFoundException;
import com.postnov.library.model.Author;
import com.postnov.library.model.Book;
import com.postnov.library.reposutory.BookRepository;
import com.postnov.library.service.EntityService.AuthorService;
import com.postnov.library.service.EntityService.BookService;
import com.postnov.library.service.EntityService.Book_AuthorService;
import com.postnov.library.service.OtherService.ConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
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

    @Transactional
    @Override
    public void saveBooks(Set<BookDto> booksDto) {

        for (BookDto bookDto : booksDto) {

            if (!bookRepository.findBookByNameAndVolume(
                    bookDto.getName(),
                    bookDto.getVolume())
                    .isPresent()) {
                Book book = convertServiceBook.convertFromDto(bookDto, Book.class);
                authorService.saveAuthors(
                        bookDto.getAuthors(),
                        bookRepository.save(book).getId());
            }
        }
    }

    @Transactional
    @Override
    public void deleteBookByBook(Book book) {
        authorService.deleteAuthorByBook(book);
        bookRepository.deleteBookByNameAndVolume(book.getName(), book.getVolume());
    }

    @Transactional
    @Override
    public void receivedBook(BookDto bookDto) {
        Long Id = getBookByBookNameAndVolume(bookDto.getName(), bookDto.getVolume()).getId();
        bookRepository.receivedBookById(Id);
    }

    @Transactional
    @Override
    public void returnBook(Long bookId) {
        bookRepository.returnBook(bookId);
    }

    @Override
    public BookDto getBookDtoByBookNameAndVolume(String name, Integer volume) {
        return makeBookDto(getBookByBookNameAndVolume(name, volume));
    }

    @Override
    public BookDto getReceivedBookDtoByBookNameAndVolume(String name, Integer volume) {
        return makeBookDto(getReceivedBookByBookNameAndVolume(name, volume));
    }

    @Override
    public BookDto getReceivedBookDtoById(Long Id) {
        return makeBookDto(getReceivedBookById(Id));
    }

    @Override
    public BookDto getBookDtoById(Long Id) throws FindBookByIdWasNotFoundException {
        return makeBookDto(getBookById(Id));
    }

    @Override
    public BookDto makeBookDto(Book book) {
        BookDto bookDto = convertServiceBook.convertToDto(book,
                BookDto.class);

        bookDto.setAuthors(
                convertServiceAuthor.convertToSetDto(
                        authorService.getAuthorsByBook(book),
                        AuthorDto.class)
        );
        return bookDto;
    }

    @Transactional
    @Override
    public Book getReceivedBookById(Long Id) {
        return bookRepository.findReceivedBookById(Id).orElseThrow(
                () -> new FindReceivedBookByIdWasNotFoundException(Id));
    }

    @Transactional
    @Override
    public Book getReceivedBookByBookNameAndVolume(String name, Integer volume) {
        return bookRepository.findReceivedBookByBookNameAndVolume(name, volume).orElseThrow(
                () -> new FindReceivedBookByNameAndVolumeWasNotFoundException(name, volume));
    }

    @Transactional
    @Override
    public Book getBookByBookNameAndVolume(String name, Integer volume) {
        return bookRepository.findReturnBookByNameAndVolume(name, volume).orElseThrow(
                () -> new FindReturnBookByNameAndVolumeWasNotFoundException(name, volume));
    }

    @Transactional
    @Override
    public Book getBookById(Long Id) throws FindBookByIdWasNotFoundException {
        return bookRepository.findBookById(Id).orElseThrow(
                () -> new FindBookByIdWasNotFoundException(Id));
    }

    @Override
    public Long getBookIdByBookDto(BookDto bookDto) {
        return getBookByBookNameAndVolume(bookDto.getName(), bookDto.getVolume()).getId();
    }

    @Transactional
    @Override
    public Set<Long> getReceivedBooksIdByBookName(String bookName) {
        return bookRepository.findReceivedBooksIdByBooksName(bookName);
    }

    @Override
    public Set<BookDto> getBooksDtoByAuthorNameAndSurname(String name, String surname) throws FindBookByIdWasNotFoundException {

        List<Author> authors = authorService.getAuthorsByNameAndSurname(name, surname);
        Iterator<Author> authorIterator = authors.iterator();
        Set<BookDto> booksDto = new HashSet<>();

        while (authorIterator.hasNext()) {
            Long author_id = authorIterator.next().getId();
            Long book_id = book_authorService.getBookIdByAuthorId(author_id);
            booksDto.add(getBookDtoById(book_id));
        }
        return booksDto;
    }

    @Override
    public Set<BookDto> getBooksDto(Long fromBookId, Long toBookId) {
        Set<BookDto> booksDto = new HashSet<>();
        for (Long i = fromBookId; i <= toBookId; ++i) {
            try {
                booksDto.add(getBookDtoById(i));
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return booksDto;
    }
}
