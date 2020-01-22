package com.postnov.library.reposutory;

import com.postnov.library.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PassportRepository extends JpaRepository<Passport, Long> {

    @Query(value = "select max(p.id) from Passport p")
    Optional<Long> findMaximalId();

    Optional<Passport> findPassportByNumberAndSeries(String number, String series);

    Optional<Passport> findPassportById(Long Id);

    void deleteById(Long Id);
}
