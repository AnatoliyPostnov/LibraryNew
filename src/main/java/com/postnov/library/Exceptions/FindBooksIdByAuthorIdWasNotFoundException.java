package com.postnov.library.Exceptions;

public class FindBooksIdByAuthorIdWasNotFoundException extends RuntimeException{

    public FindBooksIdByAuthorIdWasNotFoundException(String message){
        super(message);
    }
}
