package com.ananya.booklendingtracker2.service;

public interface LendingService {
    void lendBook(String isbn, Long memberId);
    void returnBook(String isbn);
}
