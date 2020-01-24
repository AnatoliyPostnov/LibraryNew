package com.postnov.library.Exceptions.notFoundException;

public class FindBookByNameAndVolumeWasNotFoundException extends RuntimeException {

    public FindBookByNameAndVolumeWasNotFoundException(String name, Integer volume){
        super("Book with name: " + name +
                "volume: " + volume +
                "was not found");
    }
}
