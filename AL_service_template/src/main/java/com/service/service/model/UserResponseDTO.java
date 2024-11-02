package com.service.service.model;

import com.service.service.entities.Role;
import com.service.service.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Set<String> roles;


    public UserResponseDTO() {}


    public UserResponseDTO(User user) {
        this.id = Long.valueOf(user.getId());
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

}
