package com.getir.casestudy.exception;

public class BookNotFoundException extends ExceptionResponse{
    public BookNotFoundException(){
        super("Book not found");
    }
}
