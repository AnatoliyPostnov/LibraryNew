package com.postnov.library.Exceptions;

public class FindAuthorByIdWasNotFoundException extends RuntimeException {

    public FindAuthorByIdWasNotFoundException(String message){
        super(message);
    }
}
