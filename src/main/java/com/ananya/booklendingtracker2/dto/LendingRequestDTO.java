package com.ananya.booklendingtracker2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LendingRequestDTO {

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;

    @NotNull(message = "Member ID must not be null")
    private Long memberId;
}
