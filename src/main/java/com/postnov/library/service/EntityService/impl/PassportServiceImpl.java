package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.PassportDto;
import com.postnov.library.Exceptions.notFoundException.FindPassportByIdWasNotFoundException;
import com.postnov.library.Exceptions.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.library.model.Passport;
import com.postnov.library.reposutory.PassportRepository;
import com.postnov.library.service.EntityService.PassportService;
import com.postnov.library.service.OtherService.ConvertService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    private final ConvertService<PassportDto, Passport> convertServicePassport;

    public PassportServiceImpl(PassportRepository passportRepository,
                               ConvertService<PassportDto, Passport> convertServicePassport) {
        this.passportRepository = passportRepository;
        this.convertServicePassport = convertServicePassport;
    }

    @Override
    public void deletePassportByPassportId(Long Id) {
        passportRepository.deleteById(Id);
    }

    @Override
    public PassportDto getPassportDtoById(Long Id) {
        return makePassportDto(getPassportById(Id));
    }

    @Override
    public PassportDto makePassportDto(Passport passport) {
        return convertServicePassport.convertToDto(passport, PassportDto.class);
    }

    @Override
    public Passport save(PassportDto passportDto) {
        Passport passport = convertServicePassport.convertFromDto(passportDto, Passport.class);
        return passportRepository.save(passport);
    }

    @Override
    public Passport getPassportByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return passportRepository.findPassportByNumberAndSeries(number, series).orElseThrow(
                        () -> new FindPassportByPassportNumberAndSeriesWasNotFoundException(number, series));
    }

    @Override
    public Passport getPassportById(Long Id) {
        return passportRepository.findPassportById(Id).orElseThrow(
                () -> new FindPassportByIdWasNotFoundException(Id));
    }
}
