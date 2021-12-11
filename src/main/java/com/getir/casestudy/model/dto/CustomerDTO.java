package com.getir.casestudy.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private String id;
    private String name;
    private String surname;
    private String mail;
    private List<OrderDTO> orderList;
}
