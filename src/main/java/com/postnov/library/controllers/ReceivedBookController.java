package com.postnov.library.controllers;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.service.EntityService.LibraryCardService;
import com.postnov.library.service.EntityService.ReceivedBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ReceivedBookController {

    private final LibraryCardService libraryCardService;

    private final ReceivedBookService receivedBookService;

    public ReceivedBookController(LibraryCardService libraryCardService,
                                  ReceivedBookService receivedBookService) {
        this.libraryCardService = libraryCardService;
        this.receivedBookService = receivedBookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/received/book")
    public void receivedBook(
            @RequestBody ReceivedBookDto receivedBookDto
    ) {
        System.out.println(receivedBookDto.toString());
        receivedBookService.receivedBook(receivedBookDto);
    }
}
