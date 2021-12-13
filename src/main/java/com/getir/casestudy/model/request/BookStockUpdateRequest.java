package com.getir.casestudy.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookStockUpdateRequest {
    @NotNull(message = "Book id can not be blank")
    private String id;

    @NotNull(message = "Stock information is required")
    private Long remainingStock;
}
