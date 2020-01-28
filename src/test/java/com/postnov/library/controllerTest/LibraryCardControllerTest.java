package com.postnov.library.controllerTest;

import com.postnov.library.Dto.LibraryCardDto;
import com.postnov.library.service.EntityService.LibraryCardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

public class LibraryCardControllerTest extends AbstractTestingClass {

    @Autowired
    private LibraryCardService libraryCardService;

    @Before
    public void init() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        addLibraryCardsInDbFromCreateFunc("createLibraryCard1");
        addLibraryCardsInDbFromCreateFunc("createLibraryCard2");
        addLibraryCardsInDbFromCreateFunc("createLibraryCard3");
    }

    @Test
    public void addLibraryCard() {
        Set<LibraryCardDto> libraryCards = libraryCardService.getLibraryCards(0L, LENGTH);
        Assertions.assertEquals(3, libraryCards.size());
    }

    @Test
    public void getLibraryCardByPassportNumberAndSeriesTest() throws Exception {
        String uri = "http://localhost:8080/libraryCard/by/passport/number/and/series?number=4567&series=1553445";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertLibraryCards(mvcResult);
    }

    @Test
    public void getLibraryCardsByFromLibraryCardsIdToLibraryCardsId() throws Exception {
        String uri = "http://localhost:8080/libraryCards/fromLibraryCardsId/and/toLibraryCardId?fromLibraryCardsId=0&toLibraryCardsId=500";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        assertLibraryCards(mvcResult);
    }
}
