package com.getir.casestudy.model.response;

import com.getir.casestudy.model.dto.OrderResponseDTO;
import com.getir.casestudy.model.dto.UserResponseDTO;
import lombok.Data;

@Data
public class OrderResponse {
    private OrderResponseDTO order;
    private UserResponseDTO user;
}
