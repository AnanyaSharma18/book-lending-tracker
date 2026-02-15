package com.ananya.booklendingtracker2.controller;

import com.ananya.booklendingtracker2.repository.BookRepository;
import com.ananya.booklendingtracker2.repository.MemberRepository;
import com.ananya.booklendingtracker2.model.Book;
import com.ananya.booklendingtracker2.model.Member;
import com.ananya.booklendingtracker2.service.LendingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LendingService lendingService;
    public BookRestController(BookRepository bookRepository,
                              MemberRepository memberRepository,
                              LendingService lendingService) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.lendingService = lendingService;
    }
    // --- Book endpoints ---
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            throw new IllegalArgumentException("ISBN must not be empty");
        }
        book.setAvailable(true);
        return bookRepository.save(book);
    }
    // --- Member endpoints ---
    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    @PostMapping("/members")
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }
    // --- Lending actions ---
    @PostMapping("/lend/{isbn}/{memberId}")
    public ResponseEntity<String> lendBook(@PathVariable String isbn,
                                           @PathVariable Long memberId) {
        lendingService.lendBook(isbn, memberId);
        return ResponseEntity.ok("Book lent successfully: " + isbn + " to member " +
                memberId);
    }
    @PostMapping("/return/{isbn}")
    public ResponseEntity<String> returnBook(@PathVariable String isbn) {
        lendingService.returnBook(isbn);
        return ResponseEntity.ok("Book returned successfully: " + isbn);
    }
}