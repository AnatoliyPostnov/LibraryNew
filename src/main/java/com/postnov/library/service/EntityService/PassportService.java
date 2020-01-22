package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.PassportDto;
import com.postnov.library.model.Passport;

public interface PassportService {

    void deletePassportByPassportId(Long Id);

    PassportDto findPassportById(Long Id);

    Passport save(PassportDto passportDto);

    Passport getPassportByPassportNumberAndSeries(String number, String series);
}
