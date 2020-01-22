package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.ClientDto;
import com.postnov.library.Dto.PassportDto;
import com.postnov.library.model.Client;
import com.postnov.library.model.Passport;
import com.postnov.library.reposutory.ClientRepository;
import com.postnov.library.service.EntityService.ClientService;
import com.postnov.library.service.EntityService.PassportService;
import com.postnov.library.service.OtherService.ConvertService;
import com.postnov.library.service.OtherService.Impl.CountIdServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final PassportService passportService;

    private final ConvertService<ClientDto, Client> convertServiceClient;

    public ClientServiceImpl(ClientRepository clientRepository, PassportService passportService, ConvertService<ClientDto, Client> convertServiceClient) {
        this.clientRepository = clientRepository;
        this.passportService = passportService;
        this.convertServiceClient = convertServiceClient;
    }

    @Override
    public Optional<Long> findMaximalId() {
        return clientRepository.findMaximalId();
    }

    @Override
    public void save(ClientDto clientDto) {
        passportService.save(clientDto.getPassport());
        Client client = convertServiceClient.convertFromDto(clientDto, Client.class);
        client.setPassportId(CountIdServiceImpl.Id - 1);
        client.setId(CountIdServiceImpl.Id++);
        clientRepository.save(client);
    }

    @Override
    public Map<String, Object> getMapClientWithPassportByPassportNumberAndSeries(
            String number, String series) {
        Map<String, Object> clientWithPassport = new HashMap<>();
        Passport passport = passportService.getPassportByPassportNumberAndSeries(number, series);
        Client client = clientRepository.findClientByPassportId(passport.getId()).orElseThrow(
                () -> new RuntimeException("Client with passport_id: " + passport.getId() +
                        " was not found")
        );
        clientWithPassport.put("Passport", passport);
        clientWithPassport.put("Client", client);
        return clientWithPassport;
    }

    @Override
    public ClientDto findClientById(Long Id) {
        Client client = clientRepository.findClientById(Id).orElseThrow(
                () -> new RuntimeException("Client with Id: " + Id + " was not found")
        );
        PassportDto passportDto = passportService.findPassportById(client.getPassportId());
        ClientDto clientDto = convertServiceClient.convertToDto(client, ClientDto.class);
        clientDto.setPassport(passportDto);

        return clientDto;
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
        passportService.deletePassportByPassportId(client.getPassportId());
    }
}
