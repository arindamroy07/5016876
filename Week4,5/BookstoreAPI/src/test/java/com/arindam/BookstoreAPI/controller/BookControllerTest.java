package com.arindam.BookstoreAPI.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.arindam.BookstoreAPI.dto.BookDTO;
import com.arindam.BookstoreAPI.entity.Book;
import com.arindam.BookstoreAPI.mapper.BookMapper;
import com.arindam.BookstoreAPI.service.BookService;
import com.arindam.BookstoreAPI.service.JWTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JWTService jwtService;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
        when(bookService.getAllBooks()).thenReturn(List.of(new Book(1, "Title", "Author", 100.0, "ISBN")));
        when(bookMapper.booksToBookDTOs(anyList())).thenReturn(List.of(new BookDTO(1, "Title", "Author", 100.0, "ISBN")));

        mockMvc.perform(get("/api/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title"));
    }

    @Test
    public void testGetBookById() throws Exception {
        when(bookService.getBookById(1)).thenReturn(new Book(1, "Title", "Author", 100.0, "ISBN"));
        when(bookMapper.bookToBookDTO(any(Book.class))).thenReturn(new BookDTO(1, "Title", "Author", 100.0, "ISBN"));

        mockMvc.perform(get("/api/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    public void testCreateBook() throws Exception {
        BookDTO bookDTO = new BookDTO(1, "Title", "Author", 100.0, "ISBN");
        Book book = new Book(1, "Title", "Author", 100.0, "ISBN");
        when(bookMapper.bookDTOToBook(any(BookDTO.class))).thenReturn(book);
        when(bookService.createBook(any(Book.class))).thenReturn(book);
        when(bookMapper.bookToBookDTO(any(Book.class))).thenReturn(bookDTO);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookDTO bookDTO = new BookDTO(1, "Updated Title", "Updated Author", 150.0, "Updated ISBN");
        Book book = new Book(1, "Updated Title", "Updated Author", 150.0, "Updated ISBN");
        when(bookService.getBookById(1)).thenReturn(book);
        when(bookService.updateBook(any(Book.class))).thenReturn(book);
        when(bookMapper.bookToBookDTO(any(Book.class))).thenReturn(bookDTO);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = new Book(1, "Title", "Author", 100.0, "ISBN");
        when(bookService.getBookById(1)).thenReturn(book);
        doNothing().when(bookService).deleteBook(1);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    public void testSearchBooks() throws Exception {
        when(bookService.searchBooks("Title", "Author")).thenReturn(List.of(new Book(1, "Title", "Author", 100.0, "ISBN")));

        mockMvc.perform(get("/api/books/search/Title/Author")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("ISBN"));
    }
}
