package com.learn.exceptionhandling.exception;

public class AnotherBlahException extends RuntimeException {

    public AnotherBlahException(String message) {
        super(message);
    }

    public AnotherBlahException(String message, Throwable cause) {
        super(message, cause);
    }
}
