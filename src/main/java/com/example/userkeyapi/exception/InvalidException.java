package com.example.userkeyapi.exception;

public class InvalidException extends RuntimeException{
    private String message;

    public InvalidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
