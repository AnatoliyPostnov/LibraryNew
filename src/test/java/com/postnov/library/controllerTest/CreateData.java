package com.postnov.library.controllerTest;

import com.postnov.library.Dto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateData {

    public static LibraryCardDto createLibraryCard1() {
        PassportDto passport = new PassportDto();
        passport.setName("Петя");
        passport.setSurname("Бубликов");
        passport.setNumber("4567");
        passport.setSeries("1553445");
        passport.setBirthday(LocalDate.parse("1964-06-15"));
        passport.setDateSigning(LocalDate.parse("1990-05-05"));
        passport.setAuthorityIssuer("Piter");
        ClientDto client = new ClientDto();
        client.setEmail("postnov-90@mail.ru");
        client.setPhone("89533576500");
        client.setPassport(passport);
        LibraryCardDto libraryCard = new LibraryCardDto();
        libraryCard.setClient(client);
        return libraryCard;
    }

    public static LibraryCardDto createLibraryCard2() {
        PassportDto passport = new PassportDto();
        passport.setName("Вася");
        passport.setSurname("Молодец");
        passport.setNumber("7891");
        passport.setSeries("1512345");
        passport.setBirthday(LocalDate.parse("1973-08-07"));
        passport.setDateSigning(LocalDate.parse("1995-05-05"));
        passport.setAuthorityIssuer("Moskva");
        ClientDto client = new ClientDto();
        client.setEmail("Vasia@mail.ru");
        client.setPhone("12345678987");
        client.setPassport(passport);
        LibraryCardDto libraryCard = new LibraryCardDto();
        libraryCard.setClient(client);
        return libraryCard;
    }

    public static LibraryCardDto createLibraryCard3() {
        PassportDto passport = new PassportDto();
        passport.setName("Гарик");
        passport.setSurname("Бульдог");
        passport.setNumber("1357");
        passport.setSeries("1357428");
        passport.setBirthday(LocalDate.parse("1980-06-15"));
        passport.setDateSigning(LocalDate.parse("1999-05-05"));
        passport.setAuthorityIssuer("Moskva");
        ClientDto client = new ClientDto();
        client.setEmail("Buldog@mail.ru");
        client.setPhone("98765432198");
        client.setPassport(passport);
        LibraryCardDto libraryCard = new LibraryCardDto();
        libraryCard.setClient(client);
        return libraryCard;
    }

    public static List<BookDto> createSomeBooksInDb() {
        AuthorDto authorDto1 = new AuthorDto();
        authorDto1.setName("Татьяна");
        authorDto1.setSurname("Журина");
        authorDto1.setBirthday(LocalDate.parse("1964-06-17"));

        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.setName("Неизвестный");
        authorDto2.setSurname("Автор");
        authorDto2.setBirthday(LocalDate.parse("1999-06-17"));

        Set<AuthorDto> authorsDto1 = new HashSet<>();
        authorsDto1.add(authorDto1);
        authorsDto1.add(authorDto2);

        BookDto bookDto1 = new BookDto();
        bookDto1.setName("55 устных тем по английскому языку");
        bookDto1.setVolume(155);
        bookDto1.setDateOfPublishing(LocalDate.parse("2003-05-11"));
        bookDto1.setAuthors(authorsDto1);


        AuthorDto authorDto3 = new AuthorDto();
        authorDto3.setName("Герберт");
        authorDto3.setSurname("Шилдт");
        authorDto3.setBirthday(LocalDate.parse("1977-06-17"));

        Set<AuthorDto> authorsDto2 = new HashSet<>();
        authorsDto2.add(authorDto3);

        BookDto bookDto2 = new BookDto();
        bookDto2.setName("Java. Полное руководство");
        bookDto2.setVolume(1486);
        bookDto2.setDateOfPublishing(LocalDate.parse("2019-09-16"));
        bookDto2.setAuthors(authorsDto2);


        Set<AuthorDto> authorsDto3 = new HashSet<>();
        authorsDto3.add(authorDto2);

        BookDto bookDto3 = new BookDto();
        bookDto3.setName("Неизвестная книга");
        bookDto3.setVolume(10);
        bookDto3.setDateOfPublishing(LocalDate.parse("2019-09-16"));
        bookDto3.setAuthors(authorsDto3);

        List<BookDto> booksDto = new ArrayList<>();
        booksDto.add(bookDto1);
        booksDto.add(bookDto2);
        booksDto.add(bookDto3);

        return booksDto;
    }

    public static BookDto createBookWithTreeAuthors() {
        AuthorDto authorDto1 = new AuthorDto();
        authorDto1.setName("Юлиана");
        authorDto1.setSurname("Кузьмина");
        authorDto1.setBirthday(LocalDate.of(1964, 06, 15));

        AuthorDto authorDto2 = new AuthorDto();
        authorDto2.setName("Роб");
        authorDto2.setSurname("Харроп");
        authorDto2.setBirthday(LocalDate.of(1964, 06, 15));

        AuthorDto authorDto3 = new AuthorDto();
        authorDto3.setName("Крис");
        authorDto3.setSurname("Шедер");
        authorDto3.setBirthday(LocalDate.of(1964, 06, 15));

        Set<AuthorDto> authorsDto = new HashSet<>();
        authorsDto.add(authorDto1);
        authorsDto.add(authorDto2);
        authorsDto.add(authorDto3);

        BookDto bookDto = new BookDto();
        bookDto.setName("Spring 5 для профессионалов");
        bookDto.setVolume(1120);
        bookDto.setDateOfPublishing(LocalDate.of(2019, 05, 11));
        bookDto.setAuthors(authorsDto);
        return bookDto;
    }
}
