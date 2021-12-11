package com.getir.casestudy.model.response;

import com.getir.casestudy.model.dto.OrderDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderListResponse {
    private List<OrderDTO> orders;
}
