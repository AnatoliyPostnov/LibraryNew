package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.ClientDto;
import com.postnov.library.Exceptions.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.library.model.Client;

import java.util.Map;

public interface ClientService {

    void deleteClient(Client client);

    ClientDto getClientDtoById(Long Id);

    ClientDto makeClientDto(Client client);

    Client save(ClientDto clientDto);

    Client getClientByPassportId(Long passportId);

    Client getClientById(Long Id);

    Map<String, Object> getMapClientWithPassportByPassportNumberAndSeries(
            String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

}
