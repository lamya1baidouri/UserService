package com.service.service.Exceptions;

public class CustomerBadCredentialsException extends RuntimeException {
    public CustomerBadCredentialsException(String motDePasseIncorrect) {
        super(motDePasseIncorrect);
    }
}
