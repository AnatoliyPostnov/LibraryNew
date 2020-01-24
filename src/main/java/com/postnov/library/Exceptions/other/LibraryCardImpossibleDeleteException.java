package com.postnov.library.Exceptions.other;

public class LibraryCardImpossibleDeleteException extends RuntimeException {

    public LibraryCardImpossibleDeleteException(String number, String series){
        super("Client with passport number: " + number + " series: " + series + " owes books!!!");
    }
}
