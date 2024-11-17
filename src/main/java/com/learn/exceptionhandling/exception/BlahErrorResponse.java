package com.learn.exceptionhandling.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BlahErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd\'T\'HH:mm:ss.SSS")
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
    public BlahErrorResponse {
        timestamp = LocalDateTime.now();
    }
}