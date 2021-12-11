package com.getir.casestudy.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderByDateRequest {
    @NotNull(message = "Start date is required.")
    private Date startDate;

    @NotNull(message = "End date is required.")
    private Date endDate;
}
