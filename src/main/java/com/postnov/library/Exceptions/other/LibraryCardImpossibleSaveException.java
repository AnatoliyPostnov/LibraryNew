package com.postnov.library.Exceptions.other;

import com.postnov.library.Dto.LibraryCardDto;

public class LibraryCardImpossibleSaveException extends RuntimeException {

    public LibraryCardImpossibleSaveException(LibraryCardDto libraryCardDto) {
        super("libraryCard: " + libraryCardDto.toString() + " already exist in database");
    }
}
