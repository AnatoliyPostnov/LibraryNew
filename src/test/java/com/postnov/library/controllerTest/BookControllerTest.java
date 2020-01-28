package com.postnov.library.controllerTest;

import com.postnov.library.Dto.BookDto;
import com.postnov.library.service.EntityService.BookService;
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


public class BookControllerTest extends AbstractTestingClass {

    @Autowired
    private BookService bookService;

    @Before
    public void init() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        addBooksInDbFromCreateFunc("/add/books", "createBookWithTreeAuthors");
        addBooksInDbFromCreateFunc("/add/books", "createSomeBooksInDb");
    }

    @After
    public void destroy() throws Exception {
        if (bookService.getBooksDto(0L, LENGTH).size() < 4) {
            addBooksInDbFromCreateFunc("/add/book", "createBookWithTreeAuthors");
        }
    }

    @Test
    public void getBooksByNameAndVolumeTest() throws Exception {
        String uri = "http://localhost:8080/book/by/name/and/volume?name=Spring 5 для профессионалов&volume=1120";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertBooks(mvcResult);
    }

    @Test
    public void getBooksFromBookIdToBookIdTest() throws Exception {
        String uri = "http://localhost:8080/books/by/fromBookId/and/toBookId?fromBookId=0&toBookId=500";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        assertBooks(mvcResult);
    }

    @Test
    public void getBooksByAuthorNameAndSurnameTest() throws Exception {
        String uri = "http://localhost:8080/books/by/author/name/and/surname?name=Неизвестный&surname=Автор";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertBooks(mvcResult);
    }

    @Test
    public void addBookText() {
        Set<BookDto> books = bookService.getBooksDto(0L, LENGTH);
        Assertions.assertEquals(4, books.size());
    }

    @Test
    public void deleteBookTest() throws Exception {
        String uri = "http://localhost:8080/book/by/name/and/volume?name=Spring 5 для профессионалов&volume=1120";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        assertEquals(204, status);
    }

}
