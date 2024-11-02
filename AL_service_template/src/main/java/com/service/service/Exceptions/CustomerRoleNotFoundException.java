package com.service.service.Exceptions;

public class CustomerRoleNotFoundException extends RuntimeException {
    public CustomerRoleNotFoundException(String message) {
        super(message);
    }
}