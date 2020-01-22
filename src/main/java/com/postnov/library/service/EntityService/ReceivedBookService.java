package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.model.ReceivedBook;

import java.util.Optional;

public interface ReceivedBookService {

    Optional<Long> findMaximalId();

    void receivedBook(ReceivedBookDto receivedBookDto);
}
