package com.postnov.library.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.library.Dto.AuthorDto;
import com.postnov.library.Dto.BookDto;
import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.LibraryApplication;
import com.postnov.library.model.Author;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.postnov.library.controllerTest.CreateData.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public abstract class AbstractTestingClass {

    protected static final Long LENGTH = 500L;

    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    private ModelMapper modelMapper;

    protected void assertLibraryCards(MvcResult mvcResult) throws IOException {

        assertStatus(mvcResult);

        String[] contents = countContents(mvcResult);

        for (int i = 0; i < contents.length - 1; ++i) {
            contents[i] += "]}";
        }

        List<LibraryCardDto> libraryCardsDtoTest = new ArrayList<>();
        libraryCardsDtoTest.add(createLibraryCard1());
        libraryCardsDtoTest.add(createLibraryCard2());
        libraryCardsDtoTest.add(createLibraryCard3());

        for (int i = 0; i < contents.length - 1; ++i) {
            LibraryCardDto libraryCardDto = mapFromJson(contents[i], LibraryCardDto.class);
            assert (libraryCardsDtoTest.contains(libraryCardDto));
        }
    }

    private void assertAuthors(MvcResult mvcResult) throws IOException {

        assertStatus(mvcResult);

        String[] contents = countContents(mvcResult);

        List<AuthorDto> authorsDtoTest = new ArrayList<>(
                createBookWithTreeAuthors().getAuthors());
        List<BookDto> booksDto = createSomeBooksInDb();

        for (BookDto bookDto : booksDto) {
            authorsDtoTest.addAll(bookDto.getAuthors());
        }

        List<Author> authorsTest = new ArrayList<>();
        for (AuthorDto authorDto : authorsDtoTest) {
            authorsTest.add(modelMapper.map(authorDto, Author.class));
        }

        for (int i = 0; i < contents.length - 1; ++i) {
            AuthorDto authorDto = mapFromJson(contents[i], AuthorDto.class);
            Author author = modelMapper.map(authorDto, Author.class);
            assert (authorsTest.contains(author));
        }

    }

    protected void assertBooks(MvcResult mvcResult) throws IOException {

        assertStatus(mvcResult);

        String[] contents = countContents(mvcResult);

        List<BookDto> booksDtoTest = new ArrayList<>();
        booksDtoTest.add(createBookWithTreeAuthors());
        booksDtoTest.addAll(createSomeBooksInDb());

        for (int i = 0; i < contents.length - 1; ++i) {
            BookDto bookDto = mapFromJson(contents[i], BookDto.class);
            assert (booksDtoTest.contains(bookDto));
        }
    }

    protected void assertReceivedBook(MvcResult mvcResult) throws IOException {

        assertStatus(mvcResult);

        String content = mvcResult.getResponse().getContentAsString();

        ReceivedBookDto receivedBookDto = new ReceivedBookDto();
        receivedBookDto.setBook(createBookWithTreeAuthors());
        receivedBookDto.setLibraryCard(createLibraryCard1());


        ReceivedBookDto receivedBookDtoTmp = mapFromJson(
                content.substring(1, content.length() - 1), ReceivedBookDto.class);
        assertEquals(receivedBookDtoTmp.getBook(), receivedBookDto.getBook());
        assertEquals(receivedBookDtoTmp.getLibraryCard(), receivedBookDto.getLibraryCard());
    }

    protected void receivedBookByBookAndAddInDb()
            throws Exception {
        CreateData createData = new CreateData();
        Method createLibraryCardMethod = createData.getClass().getMethod("createLibraryCard1");

        LibraryCardDto libraryCardDto = (LibraryCardDto) createLibraryCardMethod.invoke(null);

        BookDto bookDto = createBookWithTreeAuthors();

        ReceivedBookDto receivedBookDto = new ReceivedBookDto();
        receivedBookDto.setBook(bookDto);
        receivedBookDto.setLibraryCard(libraryCardDto);

        String url = "http://localhost:8080/received/book";

        mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(receivedBookDto))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
    }

    protected void returnBookByBookName()
            throws Exception {
        CreateData createData = new CreateData();

        Method createLibraryCardMethod = createData.getClass().getMethod("createLibraryCard1");

        LibraryCardDto libraryCardDto = (LibraryCardDto) createLibraryCardMethod.invoke(null);

        String number = libraryCardDto.getClient().getPassport().getNumber();
        String series = libraryCardDto.getClient().getPassport().getSeries();

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://localhost:8080/return/books/by/book/name?number=")
                .append(number)
                .append("&series=")
                .append(series)
                .append("&name=")
                .append("Spring 5 для профессионалов");

        mvc.perform(MockMvcRequestBuilders.post(urlBuilder.toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
    }

    protected void addLibraryCardsInDbFromCreateFunc(String funcName) throws Exception {
        switch (funcName) {
            case "createLibraryCard1":
                addLibraryCardsInDb(createLibraryCard1());
                break;
            case "createLibraryCard2":
                addLibraryCardsInDb(createLibraryCard2());
                break;
            case "createLibraryCard3":
                addLibraryCardsInDb(createLibraryCard3());
                break;
            default:
                throw new RuntimeException("Func: " + funcName + " is not exist");
        }
    }

    private void addLibraryCardsInDb(LibraryCardDto libraryCardDto) throws Exception {

        String libraryCardJson = "[" + mapToJson(libraryCardDto) + "]";

        System.out.println(libraryCardJson);
        mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/add/libraryCards")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(libraryCardJson)).andReturn();
    }


    protected void addBooksInDbFromCreateFunc(String uri, String funcName) throws Exception {
        if (funcName.equals("createBookWithTreeAuthors")) {
            List<BookDto> booksDto = new ArrayList<>();
            booksDto.add(createBookWithTreeAuthors());
            addBooksInDb(uri, booksDto);
        } else if (funcName.equals("createSomeBooksInDb")) {
            addBooksInDb(uri, createSomeBooksInDb());
        } else {
            throw new RuntimeException("Func: " + funcName + " is not exist");
        }
    }

    private void addBooksInDb(String uri, List<BookDto> booksDto) throws Exception {
        StringBuilder bookDtoJsonBuilder = new StringBuilder();
        String bookDtoJson;
        for (BookDto bookDto : booksDto) {
            bookDtoJsonBuilder.append(mapToJson(bookDto));
            bookDtoJsonBuilder.append(',');
        }
        bookDtoJson = "["
                + bookDtoJsonBuilder.toString()
                .substring(
                        0,
                        bookDtoJsonBuilder.length() - 1
                )
                + "]";
        System.out.println(bookDtoJson);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(bookDtoJson)).andReturn();
    }


    protected String[] countContents(MvcResult mvcResult) throws UnsupportedEncodingException {
        String content = mvcResult.getResponse().getContentAsString();
        content = content.substring(1, content.length() - 1);

        String[] contents = content.split("]},");

        for (int i = 0; i < contents.length - 1; ++i) {
            contents[i] += "]}";
        }
        return contents;
    }

    protected void assertStatus(MvcResult mvcResult) {
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
