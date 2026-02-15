package com.ananya.booklendingtracker2.controller;

import com.ananya.booklendingtracker2.repository.BookRepository;
import com.ananya.booklendingtracker2.repository.MemberRepository;
import com.ananya.booklendingtracker2.model.Book;
import com.ananya.booklendingtracker2.model.Member;
import com.ananya.booklendingtracker2.service.LendingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookViewController {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LendingService lendingService;
    public BookViewController(BookRepository bookRepository,
                              MemberRepository memberRepository,
                              LendingService lendingService) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.lendingService = lendingService;
    }
    @GetMapping("/")
    public String home(Model model) {
        List<Book> books = bookRepository.findAll();
        List<Member> members = memberRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("members", members);
        return "book-list";
    }
    @PostMapping("/ui/lend")
    public String lendFromUi(@RequestParam String isbn,
                             @RequestParam Long memberId) {
        lendingService.lendBook(isbn, memberId);
        return "redirect:/";
    }
    @PostMapping("/ui/return")
    public String returnFromUi(@RequestParam String isbn) {
        lendingService.returnBook(isbn);
        return "redirect:/";
    }
}