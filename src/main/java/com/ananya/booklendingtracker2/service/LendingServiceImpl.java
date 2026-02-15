package com.ananya.booklendingtracker2.service;

import com.ananya.booklendingtracker2.exception.BookNotAvailableException;
import com.ananya.booklendingtracker2.exception.NotFoundException;
import com.ananya.booklendingtracker2.exception.InvalidOperationException;
import com.ananya.booklendingtracker2.model.Book;
import com.ananya.booklendingtracker2.model.LendingRecord;
import com.ananya.booklendingtracker2.model.Member;
import com.ananya.booklendingtracker2.repository.BookRepository;
import com.ananya.booklendingtracker2.repository.LendingRecordRepository;
import com.ananya.booklendingtracker2.repository.MemberRepository;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LendingServiceImpl implements LendingService {

    private static final Logger log =
            LoggerFactory.getLogger(LendingServiceImpl.class);

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LendingRecordRepository lendingRecordRepository;

    public LendingServiceImpl(BookRepository bookRepository,
                              MemberRepository memberRepository,
                              LendingRecordRepository lendingRecordRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.lendingRecordRepository = lendingRecordRepository;
    }

    @Override
    @Transactional
    public void lendBook(String isbn, Long memberId) {

        log.info("Lending request received → Book: {}, Member: {}", isbn, memberId);

        Book book = bookRepository.findById(isbn)
                .orElseThrow(() ->
                        new NotFoundException("Book not found: " + isbn));

        if (!book.isAvailable()) {
            log.warn("Book {} is not available for lending", isbn);
            throw new BookNotAvailableException("Book is already lent out: " + isbn);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new NotFoundException("Member not found: " + memberId));

        LendingRecord record = LendingRecord.builder()
                .book(book)
                .member(member)
                .lentDate(LocalDateTime.now())
                .build();

        book.setAvailable(false);

        lendingRecordRepository.save(record);
        bookRepository.save(book);

        log.info("Book {} successfully lent to member {}", isbn, memberId);
    }

    @Override
    @Transactional
    public void returnBook(String isbn) {

        log.info("Return request received → Book: {}", isbn);

        Book book = bookRepository.findById(isbn)
                .orElseThrow(() ->
                        new NotFoundException("Book not found: " + isbn));

        LendingRecord activeRecord = lendingRecordRepository
                .findByBookAndReturnDateIsNull(book)
                .orElseThrow(() ->
                        new InvalidOperationException("No active lending record found for book: " + isbn));

        activeRecord.setReturnDate(LocalDateTime.now());
        book.setAvailable(true);

        lendingRecordRepository.save(activeRecord);
        bookRepository.save(book);

        log.info("Book {} successfully returned", isbn);
    }
}
