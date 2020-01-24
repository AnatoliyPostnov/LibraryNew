package com.postnov.library.controllers;

import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.Exceptions.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
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
    public void addLibraryCards(
            @RequestBody Set<LibraryCardDto> libraryCardsDto)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        libraryCardService.saveLibraryCards(libraryCardsDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/libraryCard/by/passport/number/and/series")
    public LibraryCardDto getLibraryCardByPassportNumberAndSeries(
            @RequestParam("number") String number,
            @RequestParam("series") String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return (LibraryCardDto) libraryCardService
                .getMapLibraryCardWithLibraryCardDtoByPassportNumberAndSeries(number, series)
                .get("LibraryCardDto");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/libraryCards/fromLibraryCardsId/and/toLibraryCardId")
    public Set<LibraryCardDto> getLibraryCardsByFromLibraryCardsIdToLibraryCardsId(
            @RequestParam("fromLibraryCardsId") Long fromLibraryCardsId,
            @RequestParam("toLibraryCardsId") Long toLibraryCardId) {
        return libraryCardService.getLibraryCards(fromLibraryCardsId, toLibraryCardId);
    }
}
