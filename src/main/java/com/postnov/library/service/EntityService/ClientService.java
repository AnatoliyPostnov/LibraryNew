package com.postnov.library.service.EntityService;

import com.postnov.library.Dto.ClientDto;
import com.postnov.library.model.Client;

import java.util.Map;
import java.util.Optional;

public interface ClientService {

    Optional<Long> findMaximalId();

    Client save(ClientDto clientDto);

    Map<String, Object> getMapClientWithPassportByPassportNumberAndSeries(
            String number, String series);

    ClientDto findClientById(Long Id);

    void deleteClient(Client client);
}
