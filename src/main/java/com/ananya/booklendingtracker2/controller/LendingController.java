package com.ananya.booklendingtracker2.controller;

import com.ananya.booklendingtracker2.dto.LendingRequestDTO;
import com.ananya.booklendingtracker2.dto.LendingResponseDTO;
import com.ananya.booklendingtracker2.service.LendingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/lending")
public class LendingController {

    private final LendingService lendingService;

    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @PostMapping("/lend")
    public LendingResponseDTO lendBook(@Valid @RequestBody LendingRequestDTO request) {

        lendingService.lendBook(request.getIsbn(), request.getMemberId());

        return LendingResponseDTO.builder()
                .isbn(request.getIsbn())
                .memberId(request.getMemberId())
                .lentDate(LocalDateTime.now())
                .message("Book lent successfully")
                .build();
    }

    @PostMapping("/return/{isbn}")
    public String returnBook(@PathVariable String isbn) {

        lendingService.returnBook(isbn);
        return "Book returned successfully";
    }
}
