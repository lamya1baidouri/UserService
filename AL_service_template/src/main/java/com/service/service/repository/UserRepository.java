package com.service.service.repository;

import com.service.service.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByRoles_Name(String roleName);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
