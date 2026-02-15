package com.ananya.booklendingtracker2.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LendingResponseDTO {

    private String isbn;
    private Long memberId;
    private LocalDateTime lentDate;
    private String message;
}
