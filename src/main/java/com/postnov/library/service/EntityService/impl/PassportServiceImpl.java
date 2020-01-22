package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.PassportDto;
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
    public Passport save(PassportDto passportDto) {
        Passport passport = convertServicePassport.convertFromDto(passportDto, Passport.class);
        return passportRepository.save(passport);
    }

    @Override
    public Passport getPassportByPassportNumberAndSeries(String number, String series) {
        return passportRepository.findPassportByNumberAndSeries(number, series).orElseThrow(
                        () -> new RuntimeException("Passport with number: " + number +
                                " series: " + series + " was not found")
                );
    }

    @Override
    public PassportDto findPassportById(Long Id) {
        return convertServicePassport.convertToDto(
                passportRepository.findPassportById(Id).orElseThrow(
                        () -> new RuntimeException("Passport with id: " + Id + "was not found")
                ), PassportDto.class
        );
    }

    @Override
    public void deletePassportByPassportId(Long Id) {
        passportRepository.deleteById(Id);
    }
}
