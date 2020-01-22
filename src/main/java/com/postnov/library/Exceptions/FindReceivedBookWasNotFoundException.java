package com.postnov.library.Exceptions;

public class FindReceivedBookWasNotFoundException extends RuntimeException {

    public FindReceivedBookWasNotFoundException(String message){
        super(message);
    }
}
