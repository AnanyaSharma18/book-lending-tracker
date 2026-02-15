package com.ananya.booklendingtracker2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @Column(length = 20, nullable = false, unique = true)
    private String isbn; // Primary key
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private boolean isAvailable = true; // default true
}