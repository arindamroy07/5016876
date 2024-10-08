package com.arindam.BookstoreAPI.repository;

import com.arindam.BookstoreAPI.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingAndAuthorContaining(String tile, String author);
}
