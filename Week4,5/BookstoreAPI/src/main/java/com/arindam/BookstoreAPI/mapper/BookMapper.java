package com.arindam.BookstoreAPI.mapper;

import com.arindam.BookstoreAPI.dto.BookDTO;
import com.arindam.BookstoreAPI.entity.Book;

import java.util.List;

public interface BookMapper {
    BookDTO bookToBookDTO(Book book);
    Book bookDTOToBook(BookDTO bookDTO);

    List<BookDTO> booksToBookDTOs(List<Book> books);
}
