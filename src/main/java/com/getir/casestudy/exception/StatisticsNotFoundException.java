package com.getir.casestudy.exception;

public class StatisticsNotFoundException extends ExceptionResponse{
    public StatisticsNotFoundException(){
        super("Statistics not found for given user");
    }
}
