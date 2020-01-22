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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public void returnBooks(String number, String series, String booksName) {
        Long libraryCardId =
                libraryCardService
                        .getLibraryCardIdByPassportNumberAndSeries(number, series)
                        .getId();
        Set<Long> booksId = bookService.findBooksIdByBooksName(booksName);
        for (Long bookId : booksId) {
            receivedBookRepository
                    .findReceivedBook(libraryCardId, bookId)
                    .ifPresent(receivedBook -> receivedBook.setDateOfBookReturn(LocalDate.now()));
            bookService.returnBook(bookId);
        }
    }

    @Override
    public Set<ReceivedBookDto> getReceivedBooksByPassportSNumberAndSeries(String number, String series) {
        Long libraryCardId = libraryCardService.getLibraryCardIdByPassportNumberAndSeries(number, series).getId();
        Set<ReceivedBook> receivedBooks = receivedBookRepository.findReceivedBookByLibraryCardId(libraryCardId);
        Set<ReceivedBookDto> receivedBooksDto = new HashSet<>();
        for (ReceivedBook receivedBook : receivedBooks) {
            //дописать реализацию
            receivedBooksDto.add(
                    convertServiceReceivedBook.convertToDto(receivedBook, ReceivedBookDto.class));
        }
        return receivedBooksDto;
    }
}
