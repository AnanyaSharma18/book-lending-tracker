package com.ananya.booklendingtracker2.repository;

import com.ananya.booklendingtracker2.model.Book;
import com.ananya.booklendingtracker2.model.LendingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendingRecordRepository extends JpaRepository<LendingRecord, Long> {
    // Active record: book lent out and not yet returned
    Optional<LendingRecord> findByBookAndReturnDateIsNull(Book book);
}
