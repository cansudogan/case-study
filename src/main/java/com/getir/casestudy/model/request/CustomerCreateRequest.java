package com.getir.casestudy.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerCreateRequest {
    @NotNull(message = "Customer name is required.")
    @Size(min = 3, max = 50, message = "Please use 3 to 50 characters")
    private String name;

    @NotNull(message = "Customer surname is required.")
    @Size(min = 3, max = 50, message = "Please use 3 to 50 characters")
    private String surname;

    @Email(message = "Invalid email format")
    private String email;
}
