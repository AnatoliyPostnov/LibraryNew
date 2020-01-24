package com.postnov.library.Exceptions.notFoundException;

public class FindLibraryCardByIdWasNotFoundException extends RuntimeException {

    public FindLibraryCardByIdWasNotFoundException(Long Id){
        super("LibraryCard with Id: " + Id + " was not found");
    }
}
