package com.learn.exceptionhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class AnnotatedBlahException extends RuntimeException {
    public AnnotatedBlahException(String message) {
        super(message);
    }
}
