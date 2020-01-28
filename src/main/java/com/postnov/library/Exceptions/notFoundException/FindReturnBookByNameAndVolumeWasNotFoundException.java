package com.postnov.library.Exceptions.notFoundException;

public class FindReturnBookByNameAndVolumeWasNotFoundException extends RuntimeException {

    public FindReturnBookByNameAndVolumeWasNotFoundException(String name, Integer volume) {
        super("Book with name: " + name +
                "volume: " + volume +
                "was not found");
    }
}
