package com.service.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class PatientAuthResponse {

    private String email;
    private String password;
    private Set<String> roles;

}
