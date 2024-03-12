package com.mustafa.repository;

import com.mustafa.domain.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends MongoRepository<Auth, UUID> {

    Optional<Auth> findByUsernameAndPassword(String username, String password);
    Optional<Auth> findById(String id);

}
