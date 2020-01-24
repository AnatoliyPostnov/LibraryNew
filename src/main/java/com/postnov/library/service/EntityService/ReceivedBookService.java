package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.Exceptions.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;

import java.util.Set;

public interface ReceivedBookService {

    void receivedBook(ReceivedBookDto receivedBookDto)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    void returnBooks(String number, String series, String booksName)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    Set<ReceivedBookDto> getReceivedBooksByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    Set<ReceivedBookDto> getHistoryReceivedBooksByPassportNumberAndSeries(
            String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    Set<ReceivedBookDto> getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId, Boolean scheduled) throws Exception;

    Set<ReceivedBookDto> getReceivedBooks(String number, String series, Boolean historyOrNot) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    void deleteLibraryCard(String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;
}
