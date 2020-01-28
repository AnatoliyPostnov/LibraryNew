package com.postnov.library.Exceptions.notFoundException;

public class FindLibraryCardByClientIdWasNotFoundException extends RuntimeException {

    public FindLibraryCardByClientIdWasNotFoundException(Long clientId) {
        super("LibraryCard with client_id: " + clientId +
                " was not found");
    }
}
