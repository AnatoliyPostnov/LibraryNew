package com.postnov.library.Exceptions.notFoundException;

public class FindBooksIdByAuthorIdWasNotFoundException extends RuntimeException{

    public FindBooksIdByAuthorIdWasNotFoundException(Long authorId){
        super(
                "book_id with author_id: " + authorId +
                        " was not found");
    }
}
