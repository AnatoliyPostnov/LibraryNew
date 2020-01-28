package com.postnov.library.controllerTest;

import com.postnov.library.Dto.ReceivedBookDto;
import com.postnov.library.service.EntityService.ReceivedBookService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ReceivedBookControllerTest extends AbstractTestingClass {

    @Autowired
    private ReceivedBookService receivedBookService;

    @Before
    public void init() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        addLibraryCardsInDbFromCreateFunc("createLibraryCard1");
        addLibraryCardsInDbFromCreateFunc("createLibraryCard2");
        addLibraryCardsInDbFromCreateFunc("createLibraryCard3");
        addBooksInDbFromCreateFunc("/add/books", "createBookWithTreeAuthors");
        addBooksInDbFromCreateFunc("/add/books", "createSomeBooksInDb");
        receivedBookByBookAndAddInDb();
    }

    @After
    public void destroy() throws Exception {
        returnBookByBookName();
    }

    @Test
    public void receivedBookTest() throws Exception {
        Set<ReceivedBookDto> receivedBooksDto = receivedBookService.getAllReceivedBook(0L, LENGTH, false);
        Assertions.assertEquals(1, receivedBooksDto.size());
    }

    @Test
    public void returnBooksByBookNameTest() throws Exception {
        returnBookByBookName();
        Set<ReceivedBookDto> receivedBooksDto = receivedBookService.getAllReceivedBook(0L, LENGTH, false);
        Assertions.assertEquals(0, receivedBooksDto.size());
    }

    @Test
    public void getReceivedBooksByPassportSNumberAndSeriesTest() throws Exception {
        String uri = "http://localhost:8080//received/books/by/passportS/number/and/series?number=4567&series=1553445";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertReceivedBook(mvcResult);
    }

    @Test
    public void getHistoryReceivedBooksByPassportNumberAndSeries() throws Exception {
        int count = receivedBookService.getHistoryReceivedBooksByPassportNumberAndSeries("4567", "1553445").size();
        returnBookByBookName();
        receivedBookByBookAndAddInDb();

        assertEquals(count + 1,
                receivedBookService.getHistoryReceivedBooksByPassportNumberAndSeries("4567", "1553445").size());
    }

}
