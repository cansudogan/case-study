package com.getir.casestudy.model.response;

import com.getir.casestudy.model.dto.OrderDTO;
import lombok.Data;
import org.springframework.data.domain.Page;
@Data
public class CustomerPageResponse {
    private Page<OrderDTO> orderDTO;
}
