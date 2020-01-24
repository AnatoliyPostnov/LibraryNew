package com.postnov.library.service.EntityService.impl;

import com.postnov.library.Dto.ClientDto;
import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.Dto.PassportDto;
import com.postnov.library.Exceptions.notFoundException.FindLibraryCardByClientIdWasNotFoundException;
import com.postnov.library.Exceptions.notFoundException.FindLibraryCardByIdWasNotFoundException;
import com.postnov.library.Exceptions.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.library.Exceptions.other.LibraryCardImpossibleSaveException;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    public void saveLibraryCards(Set<LibraryCardDto> libraryCardsDto) {
        for(LibraryCardDto libraryCardDto : libraryCardsDto){
            save(libraryCardDto);
        }
    }

    @Override
    public void deleteLibraryCard(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> clientWithPassport =
                clientService.getMapClientWithPassportByPassportNumberAndSeries(number, series);
        Client client = (Client) clientWithPassport.get("Client");
        libraryCardRepository.deleteByClientId(client.getId());
        clientService.deleteClient(client);
    }

    @Override
    public Map<String, Object> getMapLibraryCardWithLibraryCardDtoByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> mapLibraryCardWithClientWithPassport =
                getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(number, series);

        ClientDto clientDto = convertServiceClient.convertToDto(
                (Client) mapLibraryCardWithClientWithPassport.get("Client"), ClientDto.class);
        clientDto.setPassport(convertServicePassport.convertToDto(
                (Passport) mapLibraryCardWithClientWithPassport.get("Passport"), PassportDto.class));

        LibraryCardDto libraryCardDto =
                convertServiceLibraryCard.convertToDto(
                        (LibraryCard) mapLibraryCardWithClientWithPassport.get("LibraryCard"),
                        LibraryCardDto.class);
        libraryCardDto.setClient(clientDto);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("LibraryCard", mapLibraryCardWithClientWithPassport.get("LibraryCard"));
        resultMap.put("LibraryCardDto", libraryCardDto);

        return resultMap;
    }

    @Override
    public LibraryCardDto getLibraryCardDtoById(Long id) {
        LibraryCard libraryCard = getLibraryCardById(id);
        ClientDto clientDto = clientService.getClientDtoById(libraryCard.getClientId());
        LibraryCardDto libraryCardDto = convertServiceLibraryCard
                .convertToDto(libraryCard, LibraryCardDto.class);
        libraryCardDto.setClient(clientDto);
        return libraryCardDto;
    }

    @Override
    public LibraryCard save(LibraryCardDto libraryCardDto){
        try {
            getLibraryCardIdByLibraryCardDto(libraryCardDto);
        }catch (FindPassportByPassportNumberAndSeriesWasNotFoundException e){
            LibraryCard libraryCard = convertServiceLibraryCard
                    .convertFromDto(libraryCardDto, LibraryCard.class);
            libraryCard.setClientId(clientService.save(libraryCardDto.getClient()).getId());
            return libraryCardRepository.save(libraryCard);
        }
        throw new LibraryCardImpossibleSaveException(libraryCardDto);
    }

    @Override
    public LibraryCard getLibraryCardById(Long Id) {
        return libraryCardRepository.findLibraryCardById(Id).orElseThrow(
                () -> new FindLibraryCardByIdWasNotFoundException(Id));
    }

    @Override
    public LibraryCard getLibraryCardByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> mapLibraryCardWithClientWithPassport =
                getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(number, series);
        return (LibraryCard) mapLibraryCardWithClientWithPassport.get("LibraryCard");
    }

    @Override
    public LibraryCard getLibraryCardByClientId(Long clientId){
        return libraryCardRepository.findLibraryCardByClientId(
                clientId).orElseThrow(
                () -> new FindLibraryCardByClientIdWasNotFoundException(clientId));
    }

    @Override
    public Long getLibraryCardIdByLibraryCardDto(LibraryCardDto libraryCardDto)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Client client = (Client) getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(
                libraryCardDto.getClient().getPassport().getNumber(),
                libraryCardDto.getClient().getPassport().getSeries())
                .get("Client");
        return getLibraryCardByClientId(client.getId()).getId();
    }

    @Override
    public Set<LibraryCardDto> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId) {
        Set<LibraryCardDto> libraryCardsDto = new HashSet<>();
        for (Long i = fromLibraryCardsId; i <= toLibraryCardId; ++i){
            try {
                libraryCardsDto.add(getLibraryCardDtoById(i));
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }
        return libraryCardsDto;
    }

    @Override
    public Map<String, Object> getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(
            String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> resultMap =
                clientService.getMapClientWithPassportByPassportNumberAndSeries(number, series);
        Client client = (Client) resultMap.get("Client");
        LibraryCard libraryCard = getLibraryCardByClientId(client.getId());
        resultMap.put("LibraryCard", libraryCard);
        return resultMap;
    }

}
