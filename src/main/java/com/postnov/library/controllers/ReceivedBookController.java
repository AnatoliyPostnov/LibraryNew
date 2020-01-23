package com.postnov.library.controllers;

import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.Exceptions.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.library.model.LibraryCard;
import com.postnov.library.service.EntityService.ReceivedBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping
public class ReceivedBookController {

    private final ReceivedBookService receivedBookService;

    public ReceivedBookController(ReceivedBookService receivedBookService) {
        this.receivedBookService = receivedBookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/received/book")
    public void receivedBook(
            @RequestBody ReceivedBookDto receivedBookDto)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        receivedBookService.receivedBook(receivedBookDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/return/books/by/book/name")
    public void returnBooksByBookName(
            @RequestParam("number") String number,
            @RequestParam("series") String series,
            @RequestParam("name") String booksName)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        receivedBookService.returnBooks(number, series, booksName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/received/books/by/passportS/number/and/series")
    public Set<ReceivedBookDto> getReceivedBooksByPassportSNumberAndSeries(
            @RequestParam("number") String number,
            @RequestParam("series") String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return receivedBookService.getReceivedBooksByPassportNumberAndSeries(number, series);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/history/received/books/by/passport/number/and/series")
    public Set<ReceivedBookDto> getHistoryReceivedBooksByPassportNumberAndSeries(
            @RequestParam("number") String number,
            @RequestParam("series") String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return receivedBookService.getHistoryReceivedBooksByPassportNumberAndSeries(number, series);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all/received/books")
    public Set<ReceivedBookDto> getAllReceivedBooks(
            @RequestParam("fromReceivedBookId") Long fromReceivedBookId,
            @RequestParam("toReceivedBookId") Long toReceivedBookId) throws Exception {
        return receivedBookService.getAllReceivedBook(fromReceivedBookId, toReceivedBookId);
    }

}
