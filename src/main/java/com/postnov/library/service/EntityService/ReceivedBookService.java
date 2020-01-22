package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.ReceivedBookDto;

import java.util.Set;

public interface ReceivedBookService {

    void receivedBook(ReceivedBookDto receivedBookDto);

    void returnBooks(String number, String series, String booksName);

    Set<ReceivedBookDto> getReceivedBooksByPassportSNumberAndSeries(String number, String series);
}
