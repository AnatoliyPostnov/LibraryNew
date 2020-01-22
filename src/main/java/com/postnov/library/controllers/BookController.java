package com.postnov.library.controllers;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Exceptions.FindBookByIdWasNotFoundException;
import com.postnov.library.service.EntityService.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "add/books")
    public void addBooks(@RequestBody Set<BookDto> booksDto) {
        bookService.saveBooks(booksDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "find/book/by/name/and/volume")
    public BookDto getBook(@RequestParam("name") String name,
                           @RequestParam("volume") Integer volume){
        return bookService.findBookByBookNameAndVolume(name, volume);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "find/books/by/author/name/and/surname")
    public Set<BookDto> findBooksByAuthorNameAndSurname(@RequestParam("name") String name,
                                                       @RequestParam("surname") String surname)
            throws FindBookByIdWasNotFoundException {
        return bookService.findBooksByAuthorNameAndSurname(name, surname);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/book")
    public void deletedBookByBookNameAndVolume(@RequestParam("name") String name,
                                               @RequestParam("volume") Integer volume) {
        bookService.deleteBookByBookNameAndVolume(name, volume);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get/books")
    public Set<BookDto> getBooks(@RequestParam("fromBookId") Long fromBookId,
                                  @RequestParam("toBookId") Long toBookId) {
        return bookService.getBooks(fromBookId, toBookId);
    }

}
