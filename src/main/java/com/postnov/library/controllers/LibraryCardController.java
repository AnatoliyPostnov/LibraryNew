package com.postnov.library.controllers;

import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.service.EntityService.LibraryCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping
public class LibraryCardController {

    private final LibraryCardService libraryCardService;

    public LibraryCardController(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/libraryCards")
    public void addLibraryCards(@RequestBody Set<LibraryCardDto> libraryCardsDto) {
        libraryCardService.saveLibraryCards(libraryCardsDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/libraryCard")
    public LibraryCardDto getLibraryCard(@RequestParam("number") String number,
                                         @RequestParam("series") String series) {
        return libraryCardService.getLibraryCardByPassportNumberAndSeries(number, series);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/libraryCards")
    public Set<LibraryCardDto> getLibraryCards(@RequestParam("fromLibraryCardsId") Long fromLibraryCardsId,
                                               @RequestParam("toLibraryCardsId") Long toLibraryCardId) {
        return libraryCardService.getLibraryCards(fromLibraryCardsId, toLibraryCardId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/libraryCard")
    public void deleteLibraryCard(@RequestParam("number") String number,
                                  @RequestParam("series") String series){
        libraryCardService.deleteLibraryCard(number, series);
    }

}
