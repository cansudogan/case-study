package com.getir.casestudy.model.response;

import com.getir.casestudy.model.dto.UserStatisticDTO;
import lombok.Data;

import java.util.List;

@Data
public class CustomerStatisticResponse {
    List<UserStatisticDTO> customerStatistics;
}
