package com.postnov.library.service.OtherService.Impl;

import com.postnov.library.model.LibraryCard;
import com.postnov.library.model.ReceivedBook;
import com.postnov.library.service.EntityService.LibraryCardService;
import com.postnov.library.service.OtherService.EmailSending;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@EnableScheduling
public class EmailSendingImpl implements EmailSending {

    private final LibraryCardService libraryCardService;

    public EmailSendingImpl(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public void executeTask() {
//        List<LibraryCard> libraryCards = new ArrayList<>();
//
//        for (ReceivedBook receivedBook : getAllReceivedBook()) {
//            if (!libraryCards.contains(receivedBook.getLibraryCard())) {
//                libraryCards.add(receivedBook.getLibraryCard());
//            }
//        }
//
//        for (LibraryCard libraryCard : libraryCards) {
//            List<ReceivedBook> receivedBooks = new ArrayList<>();
//            Строчка для тестирования
//            List<ReceivedBook> receivedBooks = getReceivedBooksByLibraryCard(libraryCard);
//            for (ReceivedBook receivedBook : getReceivedBooksByLibraryCard(libraryCard)) {
//                Date date = new Date();
//                if (date.getTime() - receivedBook.getDateOfBookReceiving().getTime() >=
//                        MILLISECONDS_MONTH) {
//                    receivedBooks.add(receivedBook);
//                }
//            }
//
//            if (receivedBooks.isEmpty()) {
//                continue;
//            }
//
//
//
//            ms.send(
//                    libraryCard.getClient().getEmail(),
//                    "From library",
//                    message.toString());
//
//        }
    }

    @Override
    public String getMessage(LibraryCard libraryCard){
//        StringBuilder message = new StringBuilder("Уважаемый " +
//                libraryCard.getClient().getPassport().getName() + " " +
//                libraryCard.getClient().getPassport().getSurname() + " " +
//                "Вы должны библиотеке следующие книжки: ");
//        for (ReceivedBook receivedBook : receivedBooks) {
//            message
//                    .append(receivedBook.toString())
//                    .append(" ")
//                    .append("Дата взятия книжки: ")
//                    .append(receivedBook.getDateOfBookReceiving())
//                    .append(". Большая просьба, верните книжки");
//        }
//        return message.toString();
        return null;
    }
}
