package com.postnov.library.controllers;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Exceptions.notFoundException.FindBookByIdWasNotFoundException;
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
    @GetMapping(value = "book/by/name/and/volume")
    public BookDto getBookByNameAndVolume(
            @RequestParam("name") String name,
            @RequestParam("volume") Integer volume){
        return bookService.getBookDtoByBookNameAndVolume(name, volume);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/books/by/fromBookId/and/toBookId")
    public Set<BookDto> getBooksFromBookIdToBookId(
            @RequestParam("fromBookId") Long fromBookId,
            @RequestParam("toBookId") Long toBookId) {
        return bookService.getBooksDto(fromBookId, toBookId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "books/by/author/name/and/surname")
    public Set<BookDto> getBooksByAuthorNameAndSurname(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname)
            throws FindBookByIdWasNotFoundException {
        return bookService.getBooksDtoByAuthorNameAndSurname(name, surname);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "add/books")
    public void addBooks(
            @RequestBody Set<BookDto> booksDto) {
        bookService.saveBooks(booksDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/book/by/name/and/volume")
    public void deletedBookByBookNameAndVolume(
            @RequestParam("name") String name,
            @RequestParam("volume") Integer volume) {
        bookService.deleteBookByBookNameAndVolume(name, volume);
    }

}
