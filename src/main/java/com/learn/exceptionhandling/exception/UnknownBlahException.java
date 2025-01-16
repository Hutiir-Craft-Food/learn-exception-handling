package com.learn.exceptionhandling.exception;

public class UnknownBlahException extends RuntimeException {
    public UnknownBlahException(String message) {
        super(message);
    }
}
