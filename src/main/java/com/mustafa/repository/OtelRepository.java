package com.mustafa.repository;

import com.mustafa.domain.Otel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OtelRepository extends MongoRepository<Otel, String> {
    List<Otel> findAllByOrderByTotalPointDesc();

    List<Otel> findByGenelAciklamaContainingIgnoreCase(String search);
    //BySearchIgnoreCase

}
