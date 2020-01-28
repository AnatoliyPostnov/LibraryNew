package com.postnov.library.Exceptions.notFoundException;

public class FindClientByIdWasNotFoundException extends RuntimeException {

    public FindClientByIdWasNotFoundException(Long Id) {
        super("Client with Id: " + Id + " was not found");
    }
}
