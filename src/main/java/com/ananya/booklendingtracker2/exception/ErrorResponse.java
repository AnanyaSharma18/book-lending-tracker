package com.ananya.booklendingtracker2.exception;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;
}
