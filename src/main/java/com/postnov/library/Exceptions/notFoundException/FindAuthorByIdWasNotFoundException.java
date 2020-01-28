package com.postnov.library.Exceptions.notFoundException;

public class FindAuthorByIdWasNotFoundException extends RuntimeException {

    public FindAuthorByIdWasNotFoundException(Long authorId) {
        super("Author with" + authorId + "was not found");
    }
}
