package com.getir.casestudy.exception;

public class StockException extends ExceptionResponse{
    public StockException(){
        super("There is not enough stock");
    }
}
