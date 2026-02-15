package com.ananya.booklendingtracker2.repository;

import com.ananya.booklendingtracker2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCase(String titlePart);
}