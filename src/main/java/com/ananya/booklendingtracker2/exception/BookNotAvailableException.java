package com.ananya.booklendingtracker2.exception;


public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(String message) {
        super(message);
    }
}
