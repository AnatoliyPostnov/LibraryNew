package com.postnov.library.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.key.ZonedDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class PassportDto {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String number;

    @NotNull
    private String series;

    @NotNull
    private String authorityIssuer;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private LocalDate dateSigning;

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDateSigning() {
        return dateSigning;
    }

    public void setDateSigning(LocalDate dateSigning) {
        this.dateSigning = dateSigning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAuthorityIssuer() {
        return authorityIssuer;
    }

    public void setAuthorityIssuer(String authorityIssuer) {
        this.authorityIssuer = authorityIssuer;
    }

    @Override
    public String toString() {
        return "PassportDto{" +
                "name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                ", number='" + number + '\'' +
                ", series='" + series + '\'' +
                ", authorityIssuer='" + authorityIssuer + '\'' +
                ", birthday=" + birthday +
                ", dateSigning=" + dateSigning +
                '}';
    }
}

