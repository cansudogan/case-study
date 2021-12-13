package com.getir.casestudy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.getir.casestudy.model.request.BookCreateRequest;
import com.getir.casestudy.model.request.BookStockUpdateRequest;
import com.getir.casestudy.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testBookCreateEndpoint() throws Exception {
        BookCreateRequest request = new BookCreateRequest();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(post("/api/book").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));

    }

    @Test
    public void testBookStockUpdateEndpoint() throws Exception {
        BookStockUpdateRequest request = new BookStockUpdateRequest();

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String requestBody = objectWriter.writeValueAsString(request);

        mockMvc.perform(put("/api/book").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));
    }

    @Test
    public void testBookDeleteEndpoint() throws Exception {
        mockMvc.perform(delete("/api/book/1").contentType(MediaType.ALL_VALUE))
                .andExpect(status().isOk()).andReturn();
    }
}
