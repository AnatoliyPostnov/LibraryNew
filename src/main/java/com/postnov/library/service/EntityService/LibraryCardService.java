package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.model.LibraryCard;

import java.util.Map;
import java.util.Set;

public interface LibraryCardService {

    void saveLibraryCards(Set<LibraryCardDto> libraryCardsDto);

    void deleteLibraryCard(String number, String series);

    LibraryCardDto getLibraryCardDtoByPassportNumberAndSeries(String number, String series);

    LibraryCardDto findLibraryCardById(Long id) throws Exception;

    LibraryCard save(LibraryCardDto libraryCardDto);

    LibraryCard getLibraryCardIdByPassportNumberAndSeries(String number, String series);

    Long getLibraryCardIdByLibraryCardDto(LibraryCardDto libraryCardDto);

    Set<LibraryCardDto> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId);

    Map<String, Object> getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(
            String number, String series);
}
