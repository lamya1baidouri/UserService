package com.service.service.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@Document(collection = "users") // Specifies the collection name
public class User {

    @Id
    private String id; // Use String for MongoDB IDs

    private String username;
    private String email;
    private String password;
    private Set<Role> roles;

}
