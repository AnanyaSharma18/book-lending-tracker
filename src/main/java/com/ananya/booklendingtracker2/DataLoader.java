package com.ananya.booklendingtracker2;

import com.ananya.booklendingtracker2.repository.BookRepository;
import com.ananya.booklendingtracker2.repository.MemberRepository;
import com.ananya.booklendingtracker2.model.Book;
import com.ananya.booklendingtracker2.model.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    public DataLoader(BookRepository bookRepository,
                      MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }
    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            bookRepository.save(Book.builder()
                    .isbn("ISBN-001")
                    .title("Spring Boot Basics")
                    .isAvailable(true)
                    .build());
            bookRepository.save(Book.builder()
                    .isbn("ISBN-002")
                    .title("Java Fundamentals")
                    .isAvailable(true)
                    .build());
        }
        if (memberRepository.count() == 0) {
            memberRepository.save(Member.builder().name("Alice").build());
            memberRepository.save(Member.builder().name("Bob").build());
        }
    }
}