package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.Exceptions.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.library.model.LibraryCard;

import java.util.Map;
import java.util.Set;

public interface LibraryCardService {

    void saveLibraryCards(Set<LibraryCardDto> libraryCardsDto) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    void deleteLibraryCard(String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    Map<String, Object> getMapLibraryCardWithLibraryCardDtoByPassportNumberAndSeries(String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    LibraryCardDto getLibraryCardDtoById(Long id) throws Exception;

    LibraryCard save(LibraryCardDto libraryCardDto) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    LibraryCard getLibraryCardByPassportNumberAndSeries(String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    LibraryCard getLibraryCardByClientId(Long clientId);

    LibraryCard getLibraryCardById(Long id) throws Exception;

    Long getLibraryCardIdByLibraryCardDto(LibraryCardDto libraryCardDto) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    Set<LibraryCardDto> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId);

    Map<String, Object> getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(
            String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

}
