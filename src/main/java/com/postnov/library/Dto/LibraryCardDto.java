package com.postnov.library.Dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryCardDto that = (LibraryCardDto) o;
        return Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client);
    }
}
