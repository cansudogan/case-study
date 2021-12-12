package com.getir.casestudy.exception;

public class ExceptionResponse extends RuntimeException {
    public ExceptionResponse(String message){
        super(message);
    }
}