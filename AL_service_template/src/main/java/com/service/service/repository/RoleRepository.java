package com.service.service.repository;

import com.service.service.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends MongoRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
