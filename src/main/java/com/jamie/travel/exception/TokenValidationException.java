package com.jamie.travel.exception;

public class TokenValidationException extends RuntimeException {
    public TokenValidationException(String msg) {
        super(msg);
    }
}