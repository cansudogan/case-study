package com.getir.casestudy.model.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CustomerPageRequest {

    @NotNull(message = "Page is required")
    @Min(value = 0)
    private int page;

    @NotNull(message = "Page size is required")
    @Min(value = 0)
    private int size;
}
