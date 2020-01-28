package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.Exceptions.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.library.Exceptions.other.LibraryCardImpossibleDeleteException;
import com.postnov.library.model.Book;
import com.postnov.library.model.LibraryCard;
import com.postnov.library.model.ReceivedBook;
import com.postnov.library.reposutory.ReceivedBookRepository;
import com.postnov.library.service.EntityService.BookService;
import com.postnov.library.service.EntityService.LibraryCardService;
import com.postnov.library.service.EntityService.ReceivedBookService;
import com.postnov.library.service.OtherService.ConvertService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
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

    @Transactional
    @Override
    public void receivedBook(ReceivedBookDto receivedBookDto)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
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
        receivedBook.setDateOfBookReceiving(LocalDate.now());
        receivedBookRepository.save(receivedBook);
    }

    @Transactional
    @Override
    public void returnBooks(String number, String series, String bookName)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Long libraryCardId =
                libraryCardService
                        .getLibraryCardByPassportNumberAndSeries(number, series)
                        .getId();
        Set<Long> booksId = bookService.getReceivedBooksIdByBookName(bookName);
        for (Long bookId : booksId) {
            receivedBookRepository
                    .findReceivedBook(libraryCardId, bookId)
                    .ifPresent(receivedBook -> receivedBook.setDateOfBookReturn(LocalDate.now()));
            bookService.returnBook(bookId);
        }
    }

    @Override
    public Set<ReceivedBookDto> getReceivedBooksByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Set<ReceivedBookDto> receivedBooksDto = new HashSet<>();
        receivedBooksDto.addAll(getReceivedBooks(number, series, false));
        return receivedBooksDto;
    }

    @Override
    public List<ReceivedBookDto> getHistoryReceivedBooksByPassportNumberAndSeries(
            String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return getReceivedBooks(number, series, true);
    }

    @Transactional
    @Override
    public List<ReceivedBookDto> getReceivedBooks(String number, String series, Boolean historyOrNot)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> libraryCardWithLibraryCardDto =
                libraryCardService.getMapLibraryCardWithLibraryCardDtoByPassportNumberAndSeries(number, series);
        LibraryCard libraryCard = (LibraryCard) libraryCardWithLibraryCardDto.get("LibraryCard");

        List<ReceivedBook> receivedBooks;
        if (historyOrNot) {
            receivedBooks = receivedBookRepository.findHistoryReceivedBookByLibraryCardId(libraryCard.getId());
        } else {
            receivedBooks = receivedBookRepository.findReceivedBookByLibraryCardId(libraryCard.getId());
        }

        List<ReceivedBookDto> receivedBooksDto = new ArrayList<>();
        for (ReceivedBook receivedBook : receivedBooks) {
            ReceivedBookDto receivedBookDto = convertServiceReceivedBook.convertToDto(receivedBook, ReceivedBookDto.class);
            receivedBookDto.setBook(bookService.getReceivedBookDtoById(receivedBook.getBookId()));
            receivedBookDto.setLibraryCard((LibraryCardDto) libraryCardWithLibraryCardDto.get("LibraryCardDto"));
            receivedBooksDto.add(receivedBookDto);
        }

        return receivedBooksDto;
    }

    @Override
    public void deleteLibraryCard(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        if (getReceivedBooksByPassportNumberAndSeries(number, series).size() == 0) {
            libraryCardService.deleteLibraryCard(number, series);
        } else {
            throw new LibraryCardImpossibleDeleteException(number, series);
        }
    }

    @Transactional
    @Override
    public void deleteBookByBookNameAndVolume(String name, Integer volume) {
        Book book = bookService.getBookByBookNameAndVolume(name, volume);
        receivedBookRepository.deleteByBookId(book.getId());
        bookService.deleteBookByBook(book);
    }

    @Transactional
    @Override
    public Set<ReceivedBookDto> getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId, Boolean scheduled) throws Exception {
        Set<ReceivedBook> receivedBooks;
        if (scheduled) {
            receivedBooks = receivedBookRepository.findAllReceivedBookForScheduled();
        } else {
            receivedBooks = receivedBookRepository.findAllReceivedBook(fromReceivedBookId, toReceivedBookId);
        }
        Set<ReceivedBookDto> receivedBooksDto = new HashSet<>();
        for (ReceivedBook receivedBook : receivedBooks) {
            ReceivedBookDto receivedBookDto = convertServiceReceivedBook.convertToDto(receivedBook, ReceivedBookDto.class);
            receivedBookDto.setBook(bookService.getReceivedBookDtoById(receivedBook.getBookId()));
            receivedBookDto.setLibraryCard(libraryCardService.getLibraryCardDtoById(receivedBook.getLibraryCardId()));
            receivedBooksDto.add(receivedBookDto);
        }
        return receivedBooksDto;
    }

}
