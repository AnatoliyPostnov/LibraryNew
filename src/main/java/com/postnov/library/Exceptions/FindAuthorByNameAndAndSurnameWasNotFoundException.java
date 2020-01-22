package com.postnov.library.Exceptions;

public class FindAuthorByNameAndAndSurnameWasNotFoundException extends RuntimeException {

    public FindAuthorByNameAndAndSurnameWasNotFoundException(String message){
        super(message);
    }
}
