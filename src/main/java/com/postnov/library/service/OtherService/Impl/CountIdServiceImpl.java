package com.postnov.library.service.OtherService.Impl;

import com.postnov.library.service.EntityService.*;
import com.postnov.library.service.OtherService.CountIdService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CountIdServiceImpl implements CountIdService {

    private final AuthorService authorService;

    private final BookService bookService;

    private final Book_AuthorService book_authorService;

    private final ClientService clientService;

    private final PassportService passportService;

    private final LibraryCardService libraryCardService;

    private final ReceivedBookService receivedBookService;

    public static Long Id = 0L;

    public CountIdServiceImpl(AuthorService authorService,
                              BookService bookService,
                              Book_AuthorService book_authorService,
                              ClientService clientService,
                              PassportService passportService,
                              LibraryCardService libraryCardService,
                              ReceivedBookService receivedBookService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.book_authorService = book_authorService;
        this.clientService = clientService;
        this.passportService = passportService;
        this.libraryCardService = libraryCardService;
        this.receivedBookService = receivedBookService;
    }

    @Override
    public void countId() {
        Long authorIdMax = authorService.findMaximalId().orElse(0L);
        Long bookIdMax = bookService.findMaximalId().orElse(0L);
        Long bookAuthorIdMax = book_authorService.findMaximalId().orElse(0L);
        Long clientIdMax = clientService.findMaximalId().orElse(0L);
        Long passportIdMax = passportService.findMaximalId().orElse(0L);
        Long libraryCardIdMax = libraryCardService.findMaximalId().orElse(0L);
        Long receivedBookIdMax = receivedBookService.findMaximalId().orElse(0L);


        Id = Math.max(
                Math.max(
                        Math.max(
                                Math.max(
                                        Math.max(
                                                Math.max(
                                                        Math.max(
                                                                Id,
                                                                authorIdMax
                                                        ),
                                                        bookIdMax),
                                                bookAuthorIdMax),
                                        clientIdMax),
                                passportIdMax),
                        libraryCardIdMax),
                receivedBookIdMax) + 1;
    }

}
