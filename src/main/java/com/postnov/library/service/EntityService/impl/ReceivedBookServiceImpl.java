package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.model.ReceivedBook;
import com.postnov.library.reposutory.ReceivedBookRepository;
import com.postnov.library.service.EntityService.BookService;
import com.postnov.library.service.EntityService.LibraryCardService;
import com.postnov.library.service.EntityService.ReceivedBookService;
import com.postnov.library.service.OtherService.ConvertService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ReceivedBookServiceImpl implements ReceivedBookService {

    private final ReceivedBookRepository receivedBookRepository;

    private final LibraryCardService libraryCardService;

    private final BookService bookService;

    private final ConvertService<ReceivedBookDto, ReceivedBook> convertServiceReceivedBook;

    public ReceivedBookServiceImpl(ReceivedBookRepository receivedBookRepository,
                                   LibraryCardService libraryCardService,
                                   BookService bookService,
                                   ConvertService<ReceivedBookDto, ReceivedBook> convertServiceReceivedBook) {
        this.receivedBookRepository = receivedBookRepository;
        this.libraryCardService = libraryCardService;
        this.bookService = bookService;
        this.convertServiceReceivedBook = convertServiceReceivedBook;
    }

    @Override
    public Optional<Long> findMaximalId() {
        return receivedBookRepository.findMaximalId();
    }

    @Override
    public void receivedBook(ReceivedBookDto receivedBookDto) {
        LibraryCardDto libraryCardDto = receivedBookDto.getLibraryCard();
        BookDto bookDto = receivedBookDto.getBook();
        Long libraryCardId = libraryCardService.getLibraryCardIdByLibraryCardDto(libraryCardDto);
        Long bookId = bookService.getBookIdByBookDto(bookDto);
        bookService.receivedBook(bookDto);
        ReceivedBook receivedBook = convertServiceReceivedBook.convertFromDto(
                receivedBookDto,
                ReceivedBook.class);
        receivedBook.setBookId(bookId);
        receivedBook.setLibraryCardId(libraryCardId);
        receivedBookRepository.save(receivedBook);
    }
}
