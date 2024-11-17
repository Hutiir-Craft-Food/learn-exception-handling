package com.learn.exceptionhandling.exception;

public class BlahException extends RuntimeException {

    public BlahException(String message) {
        super(message);
    }

    public BlahException(String message, Throwable cause) {
        super(message, cause);
    }
}
