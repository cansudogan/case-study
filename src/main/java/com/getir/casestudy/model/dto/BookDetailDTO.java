package com.getir.casestudy.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BookDetailDTO {
    @NotNull(message = "Book id is required")
    private String bookId;

    @NotNull(message = "Book id count required")
    @Min(value = 1, message = "Book count must greater than zero")
    private Integer bookCount;
}
