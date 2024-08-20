package com.arindam.BookstoreAPI.service;

import com.arindam.BookstoreAPI.exception.BookNotFoundException;
import com.arindam.BookstoreAPI.repository.BookRepository;
import com.arindam.BookstoreAPI.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id " + id));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String title, String author) {
        return bookRepository.findByTitleContainingAndAuthorContaining(title, author);
    }
}
