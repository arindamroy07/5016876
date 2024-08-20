package com.arindam.BookstoreAPI.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.arindam.BookstoreAPI.dto.BookDTO;
import com.arindam.BookstoreAPI.mapper.BookMapper;
import com.arindam.BookstoreAPI.service.BookService;
import com.arindam.BookstoreAPI.entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
@Tag(name = "Book Controller", description = "API for managing books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all books", description = "Retrieve list of all books")
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOs = bookMapper.booksToBookDTOs(books);
        bookDTOs.forEach(bookDTO -> bookDTO.add(linkTo(methodOn(BookController.class).getBookById(bookDTO.getId())).withSelfRel()));
        return bookDTOs;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Get book by Id", description = "Retrieve a book by it's Id")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book!= null) {
            BookDTO bookDTO = bookMapper.bookToBookDTO(book);
            bookDTO.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Header", "seen");
            headers.add("seen at", String.valueOf(System.currentTimeMillis()));
            return new ResponseEntity<>(bookDTO, headers, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Create a new book", description = "Add a new book to the bookstore")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        try {
            Book book = bookMapper.bookDTOToBook(bookDTO);
            bookService.createBook(book);
            BookDTO responseDTO = bookMapper.bookToBookDTO(book);
            responseDTO.add(linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
            HttpHeaders headers = new HttpHeaders();
            headers.add("created at", String.valueOf(System.currentTimeMillis()));
            return new ResponseEntity<>(responseDTO, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Update a book", description = "Updating a book by it's Id")
    public  ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody BookDTO bookDTO) {
        Optional<Book> bookOptional = Optional.ofNullable(bookService.getBookById(id));
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            book.setPrice(bookDTO.getPrice());
            book.setIsbn(bookDTO.getIsbn());
            book = bookService.updateBook(book);
            BookDTO responseDTO = bookMapper.bookToBookDTO(book);
            responseDTO.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());
            HttpHeaders headers = new HttpHeaders();
            headers.add("updated at", String.valueOf(System.currentTimeMillis()));
            return new ResponseEntity<>(responseDTO, headers, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deleting a book by it's Id")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            bookService.deleteBook(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("deleted at", String.valueOf(System.currentTimeMillis()));
            return new ResponseEntity<>("Deleted", headers, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{title}/{author}")
    @Operation(summary = "Search books", description = "Searching a book by it's Title and Author")
    public ResponseEntity<List<Book>> searchBooks(@PathVariable("title") String title,
                                                  @PathVariable("author") String author) {
        return new ResponseEntity<>(bookService.searchBooks(title, author), HttpStatus.OK);
    }
}
