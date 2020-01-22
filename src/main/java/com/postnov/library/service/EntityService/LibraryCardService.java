package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.model.LibraryCard;

import java.util.Optional;
import java.util.Set;

public interface LibraryCardService {

    Optional<Long> findMaximalId();

    void save(LibraryCardDto libraryCardDto);

    void saveLibraryCards(Set<LibraryCardDto> libraryCardsDto);

    LibraryCardDto getLibraryCardByPassportNumberAndSeries(String number, String series);

    Set<LibraryCardDto> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId);

    LibraryCardDto findLibraryCardById(Long id) throws Exception;

    void deleteLibraryCard(String number, String series);

    Long getLibraryCardIdByLibraryCardDto(LibraryCardDto libraryCardDto);
}
