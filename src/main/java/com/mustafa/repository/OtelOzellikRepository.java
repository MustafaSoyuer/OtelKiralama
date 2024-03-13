package com.mustafa.repository;

import com.mustafa.domain.OtelOzellik;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtelOzellikRepository extends MongoRepository<OtelOzellik, String> {
}
