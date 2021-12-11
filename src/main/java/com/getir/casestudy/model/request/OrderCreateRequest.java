package com.getir.casestudy.model.request;

import com.getir.casestudy.model.dto.BookDetailDTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderCreateRequest {
    @NotEmpty(message = "Order list can not be empty")
    private List<BookDetailDTO> bookOrders;
}
