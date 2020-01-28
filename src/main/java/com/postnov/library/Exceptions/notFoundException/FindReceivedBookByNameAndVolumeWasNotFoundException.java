package com.postnov.library.Exceptions.notFoundException;

public class FindReceivedBookByNameAndVolumeWasNotFoundException extends RuntimeException {

    public FindReceivedBookByNameAndVolumeWasNotFoundException(String name, Integer volume) {
        super("Received book with name: " + name +
                " volume: " + volume + " was not found exception");
    }
}
