package com.getir.casestudy.model.response;

import com.getir.casestudy.domain.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderListResponse {
    private List<Order> orders;
}
