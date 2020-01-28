package com.postnov.library.Exceptions.notFoundException;

public class FindPassportByPassportNumberAndSeriesWasNotFoundException extends Exception {

    public FindPassportByPassportNumberAndSeriesWasNotFoundException(String number, String series) {
        super("Passport with number: " + number +
                " series: " + series + " was not found");
    }
}
