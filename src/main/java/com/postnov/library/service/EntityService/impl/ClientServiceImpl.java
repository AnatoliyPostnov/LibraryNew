package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.ClientDto;
import com.postnov.library.Dto.PassportDto;
import com.postnov.library.Exceptions.notFoundException.FindClientByIdWasNotFoundException;
import com.postnov.library.Exceptions.notFoundException.FindClientByPassportIdWasNotFoundException;
import com.postnov.library.Exceptions.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.library.model.Client;
import com.postnov.library.model.Passport;
import com.postnov.library.reposutory.ClientRepository;
import com.postnov.library.service.EntityService.ClientService;
import com.postnov.library.service.EntityService.PassportService;
import com.postnov.library.service.OtherService.ConvertService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final PassportService passportService;

    private final ConvertService<ClientDto, Client> convertServiceClient;

    public ClientServiceImpl(ClientRepository clientRepository,
                             PassportService passportService,
                             ConvertService<ClientDto, Client> convertServiceClient) {
        this.clientRepository = clientRepository;
        this.passportService = passportService;
        this.convertServiceClient = convertServiceClient;
    }

    @Transactional
    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
        passportService.deletePassportByPassportId(client.getPassportId());
    }

    @Override
    public ClientDto getClientDtoById(Long Id) {
        return makeClientDto(getClientById(Id));
    }

    @Override
    public ClientDto makeClientDto(Client client) {
        PassportDto passportDto = passportService.getPassportDtoById(client.getPassportId());
        ClientDto clientDto = convertServiceClient.convertToDto(client, ClientDto.class);
        clientDto.setPassport(passportDto);
        return clientDto;
    }

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client = convertServiceClient.convertFromDto(clientDto, Client.class);
        client.setPassportId(passportService.save(clientDto.getPassport()).getId());
        return clientRepository.save(client);
    }

    @Transactional
    @Override
    public Client getClientByPassportId(Long passportId) {
        return clientRepository.findClientByPassportId(passportId).orElseThrow(
                () -> new FindClientByPassportIdWasNotFoundException(passportId));
    }

    @Transactional
    @Override
    public Client getClientById(Long Id) {
        return clientRepository.findClientById(Id).orElseThrow(
                () -> new FindClientByIdWasNotFoundException(Id));
    }

    @Override
    public Map<String, Object> getMapClientWithPassportByPassportNumberAndSeries(
            String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> clientWithPassport = new HashMap<>();
        Passport passport = passportService.getPassportByPassportNumberAndSeries(number, series);
        Client client = getClientByPassportId(passport.getId());
        clientWithPassport.put("Passport", passport);
        clientWithPassport.put("Client", client);
        return clientWithPassport;
    }
}
