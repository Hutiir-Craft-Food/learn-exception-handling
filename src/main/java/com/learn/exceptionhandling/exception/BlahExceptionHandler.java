package com.learn.exceptionhandling.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BlahExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BlahException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Object handleBlahException(RuntimeException ex,
                                      HandlerMethod handlerMethod,
                                      HttpServletRequest request) {

        return BlahErrorResponse.builder()
                .status(HttpStatus.I_AM_A_TEAPOT.value())
                .error(HttpStatus.I_AM_A_TEAPOT.getReasonPhrase())
                // todo:
                //  1.  check header Accept-Language in request
                //  2. if Accept-Language header  is not set,
                //      check user preferences, need to get principal
                //  based on that, identify user's locale
                .message(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler({AnotherBlahException.class})
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Object handleAnotherBlahException(RuntimeException ex,
                                      HandlerMethod handlerMethod,
                                      HttpServletRequest request) {

        return BlahErrorResponse.builder()
                .status(HttpStatus.I_AM_A_TEAPOT.value())
                .error(HttpStatus.I_AM_A_TEAPOT.getReasonPhrase())
                .message(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .build();
    }
}
