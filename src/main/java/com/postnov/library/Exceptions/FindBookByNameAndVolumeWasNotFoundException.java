package com.postnov.library.Exceptions;

public class FindBookByNameAndVolumeWasNotFoundException extends RuntimeException {

    public FindBookByNameAndVolumeWasNotFoundException(String message){
        super(message);
    }
}
