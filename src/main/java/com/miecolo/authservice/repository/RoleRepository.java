package com.miecolo.authservice.repository;

import com.miecolo.authservice.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {
}
