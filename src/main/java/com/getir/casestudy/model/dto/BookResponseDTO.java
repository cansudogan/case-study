package com.getir.casestudy.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookResponseDTO {
    private String title;
    private String author;
    private BigDecimal price;
}
