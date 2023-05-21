package com.orekhov.webfluxsecurity.exception;

public class AuthenticationException extends ApiException {
    public AuthenticationException(String message, String errorCode) {
        super(message, errorCode);
    }
}
