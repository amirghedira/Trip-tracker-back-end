package com.miecolo.authservice.repository;

import com.miecolo.authservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findUserByUsernameOrEmail(String username,String email);
    Optional<User> findUserByActivatedIsTrueAndUsernameOrEmail(String username,String email);
    Optional<User> findUserSuspendedIsFalseAndByUsernameOrEmail(String username,String email);

    Optional<User> findUserByUsername(String username);
    Boolean existsByUsernameOrEmail(String username,String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    List<User> findAllByIdIn(List<String> ids);


}
