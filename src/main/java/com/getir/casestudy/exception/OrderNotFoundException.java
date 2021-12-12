package com.getir.casestudy.exception;

public class OrderNotFoundException extends ExceptionResponse{
    public OrderNotFoundException(){
        super("Book count can not be negative");
    }
}
