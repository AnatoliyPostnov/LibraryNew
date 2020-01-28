package com.postnov.library.service.OtherService.Impl;

import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.service.EntityService.ReceivedBookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@EnableScheduling
public class EmailSending {

    private static final Long MILLISECONDS_MONTH = 2592000000L;

    private final ReceivedBookService receivedBookService;

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String username;

    public EmailSending(ReceivedBookService receivedBookService,
                        JavaMailSender sender) {
        this.receivedBookService = receivedBookService;
        this.sender = sender;
    }

    private void send(String mailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        sender.send(mailMessage);
    }

    @Scheduled(fixedRate = 10000)
    private void executeTask() throws Exception {
        List<LibraryCardDto> libraryCardsDto = new ArrayList<>();

        for (ReceivedBookDto receivedBookDto : receivedBookService.getAllReceivedBook(0L, 0L, true)) {
            if (!libraryCardsDto.contains(receivedBookDto.getLibraryCard())) {
                libraryCardsDto.add(receivedBookDto.getLibraryCard());
            }
        }

        for (LibraryCardDto libraryCardDto : libraryCardsDto) {
            Set<ReceivedBookDto> receivedBooksDtoByLibraryCard = new HashSet<>();
//            Строчка для тестирования
//            Set<ReceivedBookDto> receivedBooksDtoByLibraryCard = receivedBookService.getReceivedBooksByPassportNumberAndSeries(
//                    libraryCardDto.getClient().getPassport().getNumber(),
//                    libraryCardDto.getClient().getPassport().getSeries());
            for (ReceivedBookDto receivedBookDto : receivedBookService.getReceivedBooksByPassportNumberAndSeries(
                    libraryCardDto.getClient().getPassport().getNumber(),
                    libraryCardDto.getClient().getPassport().getSeries())) {
                LocalDateTime timeNow = LocalDateTime.now();
                LocalDateTime timeDateOfBookReceiving = LocalDateTime.of(receivedBookDto.getDateOfBookReceiving(), LocalTime.now());

                if (timeNow.getYear() != timeDateOfBookReceiving.getYear() ||
                        timeNow.getDayOfYear() - timeDateOfBookReceiving.getDayOfYear() >=
                                timeNow.getDayOfMonth()) {
                    receivedBooksDtoByLibraryCard.add(receivedBookDto);
                }
            }

            if (receivedBooksDtoByLibraryCard.isEmpty()) {
                continue;
            }

            send(
                    libraryCardDto.getClient().getEmail(),
                    "Message from library",
                    getMessage(libraryCardDto, receivedBooksDtoByLibraryCard));
        }
    }

    private String getMessage(LibraryCardDto libraryCardDto, Set<ReceivedBookDto> receivedBooksDtoByLibraryCard) {
        StringBuilder message = new StringBuilder("Уважаемый " +
                libraryCardDto.getClient().getPassport().getName() + " " +
                libraryCardDto.getClient().getPassport().getSurname() +
                " Вы должны библиотеке следующие книжки: ");
        for (ReceivedBookDto receivedBookDto : receivedBooksDtoByLibraryCard) {
            message
                    .append(receivedBookDto.toString())
                    .append(" Дата взятия книжки: ")
                    .append(receivedBookDto.getDateOfBookReceiving())
                    .append(". Большая просьба, верните книжки");
        }
        return message.toString();
    }
}