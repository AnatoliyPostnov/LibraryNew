package com.postnov.library.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "libraryCard")
public class LibraryCard implements Serializable {

    @Id
    private Long id;

    @Column
    private Long clientId;

    public LibraryCard(){}

    public LibraryCard(Long id, Long clientId) {
        this.id = id;
        this.clientId = clientId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
