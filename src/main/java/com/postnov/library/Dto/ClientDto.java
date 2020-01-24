package com.postnov.library.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
}