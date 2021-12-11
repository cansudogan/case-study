package com.getir.casestudy.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String id;
    private String userId;
    private BigDecimal totalPrice;
    private Date dateCreated;
    private List<BookDTO> bookList;
    private Long totalBookCount;
}
