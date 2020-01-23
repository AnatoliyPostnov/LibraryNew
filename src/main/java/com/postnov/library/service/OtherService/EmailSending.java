package com.postnov.library.service.OtherService;

import com.postnov.library.model.LibraryCard;

public interface EmailSending {

    void executeTask();

    String getMessage(LibraryCard libraryCard);

}
