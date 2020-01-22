package com.postnov.library.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private Long passportId;

    public Client(){
    }

    public Client(String phone, String email, Long passportId) {
        this.phone = phone;
        this.email = email;
        this.passportId = passportId;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    @Override
    public String toString() {
        return "Client - id: " + id + " phone: "
                + phone + " email: " + email
                + " passport_id:" + passportId;
    }

}
