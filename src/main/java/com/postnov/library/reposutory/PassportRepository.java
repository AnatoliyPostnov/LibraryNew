package com.postnov.library.reposutory;

import com.postnov.library.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassportRepository extends JpaRepository<Passport, Long> {

    Optional<Passport> findPassportByNumberAndSeries(String number, String series);

    Optional<Passport> findPassportById(Long Id);

    void deleteById(Long Id);
}
