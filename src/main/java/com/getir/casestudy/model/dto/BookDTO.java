package com.getir.casestudy.model.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String id;
    private String title;
    private String description;
    private String author;
    private Double price;
    private Long remainingStock;
}
