package com.arindam.BookstoreAPI.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.arindam.BookstoreAPI.dto.BookDTO;
import com.arindam.BookstoreAPI.entity.Book;
import com.arindam.BookstoreAPI.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class BookControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    String token;

    @BeforeEach
    void setUp() throws Exception {
        bookRepository.deleteAll();

        String userJson = "{\"username\":\"ari\",\"password\":\"ari@123\"}";
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andReturn();

        String token = mvcResult.getResponse().getContentAsString();
        this.token = "Bearer " + token;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book = new Book(1, "Title1", "Author1", 100.0, "Isbn1");
        bookRepository.save(book);

        mockMvc.perform(get("/api/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[0].author").value("Author1"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(1, "Title1", "Author1", 100.0, "Isbn1");
        book = bookRepository.save(book);

        mockMvc.perform(get("/api/books/" + book.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title1"))
                .andExpect(jsonPath("$.author").value("Author1"));
    }

    @Test
    public void testCreateBook() throws Exception {
        BookDTO bookDTO = new BookDTO(1, "Title1", "Author1", 100.0, "Isbn1");

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Title1"))
                .andExpect(jsonPath("$.author").value("Author1"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book(1, "Title1", "Author1", 100.0, "Isbn1");
        book = bookRepository.save(book);
        BookDTO bookDTO = new BookDTO(book.getId(), "Updated Title", "Updated Author", 150.0, "Updated Isbn");

        mockMvc.perform(put("/api/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = new Book(1, "Title1", "Author1", 100.0, "Isbn1");
        book = bookRepository.save(book);

        mockMvc.perform(delete("/api/books/" + book.getId())
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/" + book.getId())
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchBooks() throws Exception {
        Book book = new Book(1, "Title1", "Author1", 100.0, "Isbn1");
        book = bookRepository.save(book);

        mockMvc.perform(get("/api/books/search/" + book.getTitle() + "/" + book.getAuthor())
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title1"))
                .andExpect(jsonPath("$[0].author").value("Author1"));
    }
}
