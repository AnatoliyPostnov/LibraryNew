package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.ClientDto;
import com.postnov.library.model.Client;

import java.util.Map;

public interface ClientService {

    void deleteClient(Client client);

    ClientDto findClientById(Long Id);

    Client save(ClientDto clientDto);

    Map<String, Object> getMapClientWithPassportByPassportNumberAndSeries(
            String number, String series);

}
