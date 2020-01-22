package com.postnov.library.controllers;

import com.postnov.library.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@ControllerAdvice
@RequestMapping
public class ExceptionController {

    @ExceptionHandler({
            FindAuthorByIdWasNotFoundException.class,
            FindAuthorByNameAndAndSurnameWasNotFoundException.class,
            FindBookByIdWasNotFoundException.class,
            FindBookByNameAndVolumeWasNotFoundException.class,
            FindBooksIdByAuthorIdWasNotFoundException.class
    })
    protected ResponseEntity<RuntimeException> applicationException(RuntimeException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

}
