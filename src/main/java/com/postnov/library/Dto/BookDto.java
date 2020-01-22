package com.postnov.library.Dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class BookDto implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private Integer volume;

    @NotNull
    private LocalDate dateOfPublishing;

    @NotNull
    private Set<AuthorDto> authors = new HashSet<>();

    public LocalDate getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(LocalDate dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                ", dateOfPublishing=" + dateOfPublishing +
                ", authors=" + authors +
                '}';
    }
}