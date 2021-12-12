package com.getir.casestudy.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;

@Data
public class UserStatisticDTO {
    private Month month;
    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalPurchasedAmount;
}
