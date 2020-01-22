package com.postnov.library.Dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LibraryCardDto implements Serializable {

    @NotNull
    private ClientDto client;

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "LibraryCardDto{" +
                "client=" + client +
                '}';
    }
}
