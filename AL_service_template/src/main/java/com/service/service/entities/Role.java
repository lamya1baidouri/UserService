package com.service.service.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "roles") // Specifies the collection name
public class Role {

    @Id
    private String id; // Use String for MongoDB IDs

    private String name;

    // Getters and Setters
}
