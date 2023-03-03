package com.endava.demo.exception;

public class TUIException extends RuntimeException{

    private TUIExceptionType exceptionType;
    private String description;


    public TUIException(TUIExceptionType exceptionType, String description) {
        this.exceptionType = exceptionType;
        this.description = description;
    }


    public TUIExceptionType getTUIExceptionType() {
        return exceptionType;
    }

    public String getDescription() {
        return description;
    }
}
