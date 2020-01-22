package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.ClientDto;
import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.Dto.PassportDto;
import com.postnov.library.model.Client;
import com.postnov.library.model.LibraryCard;
import com.postnov.library.model.Passport;
import com.postnov.library.reposutory.LibraryCardRepository;
import com.postnov.library.service.EntityService.ClientService;
import com.postnov.library.service.EntityService.LibraryCardService;
import com.postnov.library.service.OtherService.ConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class LibraryCardServiceImpl implements LibraryCardService {

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final LibraryCardRepository libraryCardRepository;

    private final ClientService clientService;

    private final ConvertService<LibraryCardDto, LibraryCard> convertServiceLibraryCard;

    private final ConvertService<ClientDto, Client> convertServiceClient;

    private final ConvertService<PassportDto, Passport> convertServicePassport;

    public LibraryCardServiceImpl(LibraryCardRepository libraryCardRepository,
                                  ClientService clientService,
                                  ConvertService<LibraryCardDto, LibraryCard> convertServiceLibraryCard,
                                  ConvertService<ClientDto, Client> convertServiceClient,
                                  ConvertService<PassportDto, Passport> convertServicePassport) {
        this.libraryCardRepository = libraryCardRepository;
        this.clientService = clientService;
        this.convertServiceLibraryCard = convertServiceLibraryCard;
        this.convertServiceClient = convertServiceClient;
        this.convertServicePassport = convertServicePassport;
    }

    @Override
    public Optional<Long> findMaximalId() {
        return libraryCardRepository.findMaximalId();
    }

    @Override
    public LibraryCard save(LibraryCardDto libraryCardDto) {
        LibraryCard libraryCard = convertServiceLibraryCard
                .convertFromDto(libraryCardDto, LibraryCard.class);
        libraryCard.setClientId(clientService.save(libraryCardDto.getClient()).getId());
        return libraryCardRepository.save(libraryCard);
    }

    @Override
    public void saveLibraryCards(Set<LibraryCardDto> libraryCardsDto) {
        for(LibraryCardDto libraryCardDto : libraryCardsDto){
            save(libraryCardDto);
        }
    }

    @Override
    public LibraryCardDto getLibraryCardByPassportNumberAndSeries(String number, String series) {
        Map<String, Object> clientWithPassport =
                clientService.getMapClientWithPassportByPassportNumberAndSeries(number, series);

        Passport passport = (Passport) clientWithPassport.get("Passport");
        Client client = (Client) clientWithPassport.get("Client");

        LibraryCard libraryCard = libraryCardRepository.findLibraryCardByClientId(
                client.getId()).orElseThrow(
                () -> new RuntimeException("LibraryCard with client_id: " + client.getId() +
                        " was not found")
        );

        ClientDto clientDto = convertServiceClient.convertToDto(client, ClientDto.class);
        clientDto.setPassport(convertServicePassport.convertToDto(passport, PassportDto.class));

        LibraryCardDto libraryCardDto =
                convertServiceLibraryCard.convertToDto(libraryCard, LibraryCardDto.class);
        libraryCardDto.setClient(clientDto);

        return libraryCardDto;
    }

    @Override
    public Set<LibraryCardDto> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId) {
        Set<LibraryCardDto> libraryCardsDto = new HashSet<>();
        for (Long i = fromLibraryCardsId; i <= toLibraryCardId; ++i){
            try {
                libraryCardsDto.add(findLibraryCardById(i));
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }
        return libraryCardsDto;
    }

    @Override
    public LibraryCardDto findLibraryCardById(Long id) throws Exception {
        LibraryCard libraryCard = libraryCardRepository.findLibraryCardById(id).orElseThrow(
                () -> new Exception("LibraryCard with id: " + id + " was not found")
        );
        ClientDto clientDto = clientService.findClientById(libraryCard.getClientId());
        LibraryCardDto libraryCardDto = convertServiceLibraryCard
                .convertToDto(libraryCard, LibraryCardDto.class);
        libraryCardDto.setClient(clientDto);
        return libraryCardDto;
    }

    @Override
    public void deleteLibraryCard(String number, String series) {
        Map<String, Object> clientWithPassport =
                clientService.getMapClientWithPassportByPassportNumberAndSeries(number, series);
        Client client = (Client) clientWithPassport.get("Client");
        libraryCardRepository.deleteByClientId(client.getId());
        clientService.deleteClient(client);
    }

    @Override
    public Long getLibraryCardIdByLibraryCardDto(LibraryCardDto libraryCardDto) {
        Map<String, Object> clientWithPassport =
                clientService.getMapClientWithPassportByPassportNumberAndSeries(
                libraryCardDto.getClient().getPassport().getNumber(),
                libraryCardDto.getClient().getPassport().getSeries()
        );
        Client client = (Client) clientWithPassport.get("Client");
        return libraryCardRepository.findLibraryCardByClientId(client.getId()).orElseThrow(
                () -> new RuntimeException("LibraryCard with clientId: " + client.getId() +
                        " was not found")
        ).getId();
    }

}
