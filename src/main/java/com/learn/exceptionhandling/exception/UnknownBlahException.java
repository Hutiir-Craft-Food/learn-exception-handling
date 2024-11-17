package com.learn.exceptionhandling.exception;

public class UnknownBlahException extends RuntimeException {

    public UnknownBlahException(String message) {
        super(message);
    }

    public UnknownBlahException(String message, Throwable cause) {
        super(message, cause);
    }
}
