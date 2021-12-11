package com.getir.casestudy.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookCreateRequest {
    @NotEmpty(message = "Book title is required")
    private String title;

    private String description;

    @NotEmpty(message = "Book's author is required")
    private String author;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price can not be less than zero.")
    private BigDecimal price;

    @NotNull(message = "Stock can not be null.")
    @Min(value = 1, message = "RemainingStock can not be less than zero.")
    private Long remainingStock;
}
