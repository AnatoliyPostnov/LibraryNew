package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.PassportDto;
import com.postnov.library.model.Passport;

import java.util.Optional;

public interface PassportService {

    Optional<Long> findMaximalId();

    void save(PassportDto passportDto);

    Passport getPassportByPassportNumberAndSeries(String number, String series);

    PassportDto findPassportById(Long Id);

    void deletePassportByPassportId(Long Id);
}
