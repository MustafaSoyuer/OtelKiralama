package com.mustafa.repository;

import com.mustafa.domain.UserFavoriOtel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserFavoriOtelRepository extends MongoRepository<UserFavoriOtel, String> {
    List<UserFavoriOtel> findByUserId(String id);

}
