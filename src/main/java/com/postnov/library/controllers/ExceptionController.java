package com.postnov.library.controllers;

import com.postnov.library.Exceptions.notFoundException.*;
import com.postnov.library.Exceptions.other.LibraryCardImpossibleDeleteException;
import com.postnov.library.Exceptions.other.LibraryCardImpossibleSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@ControllerAdvice
@RequestMapping
public class ExceptionController {

    @ExceptionHandler({
            FindClientByPassportIdWasNotFoundException.class,
            FindLibraryCardByIdWasNotFoundException.class,
            FindAuthorByIdWasNotFoundException.class,
            FindBooksIdByAuthorIdWasNotFoundException.class,
            FindAuthorByNameAndAndSurnameWasNotFoundException.class,
            FindBookByNameAndVolumeWasNotFoundException.class,
            FindClientByIdWasNotFoundException.class,
            FindLibraryCardByClientIdWasNotFoundException.class,
            FindBookByIdWasNotFoundException.class,
            FindPassportByIdWasNotFoundException.class,
            FindPassportByPassportNumberAndSeriesWasNotFoundException.class,
            FindReceivedBookByIdWasNotFoundException.class,
            FindReceivedBookByNameAndVolumeWasNotFoundException.class
    })
    protected ResponseEntity<RuntimeException> applicationExceptionNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            LibraryCardImpossibleDeleteException.class,
            LibraryCardImpossibleSaveException.class
    })
    protected ResponseEntity<RuntimeException> applicationExceptionForbidden(RuntimeException ex) {
        return new ResponseEntity<>(ex, HttpStatus.FORBIDDEN);
    }
}
