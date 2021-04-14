package com.miecolo.authservice.repository;

import com.miecolo.authservice.entity.Authority;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorityRepository extends MongoRepository<Authority,String> {
}
