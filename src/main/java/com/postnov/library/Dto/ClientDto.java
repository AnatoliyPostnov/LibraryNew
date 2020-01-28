package com.postnov.library.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class ClientDto implements Serializable {

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @Email
    @NotBlank
    private String email;

    @NotNull
    private PassportDto passport;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PassportDto getPassport() {
        return passport;
    }

    public void setPassport(PassportDto passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passport=" + passport +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(phone, clientDto.phone) &&
                Objects.equals(email, clientDto.email) &&
                Objects.equals(passport, clientDto.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email, passport);
    }
}