package com.postnov.library.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "passport")
public class Passport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String number;

    @Column
    private String series;

    @Column
    private String authorityIssuer;

    @Column
    private LocalDate birthday;

    @Column
    private LocalDate dateSigning;

    public Passport() {}

    public Passport(String name,
                    String surname,
                    LocalDate birthday,
                    String number,
                    String series,
                    String authorityIssuer,
                    LocalDate dateSigning) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.number = number;
        this.series = series;
        this.authorityIssuer = authorityIssuer;
        this.dateSigning = dateSigning;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "passport: id: " + id + " name: "
                + name + " surname:" + surname
                + " birthday: " + birthday + " number: "
                + number + " series: " + series
                + " authorityIssuer: " + authorityIssuer +
                " dateSigning: " + dateSigning;
    }
}
