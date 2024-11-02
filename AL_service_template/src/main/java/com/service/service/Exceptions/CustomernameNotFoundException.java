package com.service.service.Exceptions;

public class CustomernameNotFoundException extends RuntimeException {
    public CustomernameNotFoundException(String utilisateurNonTrouvé) {
        super(utilisateurNonTrouvé);
    }
}
