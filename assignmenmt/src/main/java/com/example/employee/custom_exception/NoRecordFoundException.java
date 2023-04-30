package com.example.employee.custom_exception;

public class NoRecordFoundException extends RuntimeException{
    public NoRecordFoundException(String message) {
        super(message);
    }
}
