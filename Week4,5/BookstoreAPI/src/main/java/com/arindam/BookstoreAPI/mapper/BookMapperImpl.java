package com.arindam.BookstoreAPI.mapper;

import com.arindam.BookstoreAPI.dto.BookDTO;
import com.arindam.BookstoreAPI.entity.Book;
import com.arindam.BookstoreAPI.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapperImpl implements BookMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }
        Book book = new Book();
        book = modelMapper.map(bookDTO, Book.class);
        return book;
    }

    @Override
    public List<BookDTO> booksToBookDTOs(List<Book> books) {
        if ( books == null ) {
            return null;
        }
        return bookRepository.findAll()
                .stream()
                .map(this::bookToBookDTO)
                .collect(Collectors.toList());
    }
}
