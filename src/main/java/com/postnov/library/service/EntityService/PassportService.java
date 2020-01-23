package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.Dto.PassportDto;
import com.postnov.library.Exceptions.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.library.model.Book;
import com.postnov.library.model.Passport;

public interface PassportService {

    void deletePassportByPassportId(Long Id);

    PassportDto getPassportDtoById(Long Id);

    PassportDto makePassportDto(Passport passport);

    Passport getPassportById(Long Id);

    Passport save(PassportDto passportDto);

    Passport getPassportByPassportNumberAndSeries(String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;
}
