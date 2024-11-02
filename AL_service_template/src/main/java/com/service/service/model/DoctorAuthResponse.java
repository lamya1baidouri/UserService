package com.service.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
public class DoctorAuthResponse {

    private String email;
    private String password;
    private Set<String> roles;


}
