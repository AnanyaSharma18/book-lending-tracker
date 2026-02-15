package com.ananya.booklendingtracker2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LendingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId; // Primary key
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_isbn")
    private Book book;
    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;
    private LocalDateTime lentDate;
    private LocalDateTime returnDate; // null when not returned yet
}