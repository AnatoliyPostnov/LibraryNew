package com.postnov.library.controllers;

import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.service.EntityService.ReceivedBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void receivedBook(@RequestBody ReceivedBookDto receivedBookDto) {
        receivedBookService.receivedBook(receivedBookDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/return/books")
    public void returnBooksByBookName(
            @RequestParam("number") String number,
            @RequestParam("series") String series,
            @RequestParam("name") String booksName) {
        receivedBookService.returnBooks(number, series, booksName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/received/books/by/passportS/number/and/series")
    public Set<ReceivedBookDto> getReceivedBooksByPassportSNumberAndSeries(
            @RequestParam("number") String number,
            @RequestParam("series") String series) {
        return receivedBookService.getReceivedBooksByPassportSNumberAndSeries(number, series);
    }

}
