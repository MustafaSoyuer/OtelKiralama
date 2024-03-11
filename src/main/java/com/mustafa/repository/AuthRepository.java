package com.mustafa.repository;

import com.mustafa.domain.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AuthRepository extends MongoRepository<Auth, UUID> {
}
