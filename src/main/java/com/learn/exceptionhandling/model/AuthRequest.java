package com.learn.exceptionhandling.model;

import org.springframework.lang.NonNull;

public record AuthRequest (
        @NonNull
        String username,

        @NonNull
        String password
) { }
