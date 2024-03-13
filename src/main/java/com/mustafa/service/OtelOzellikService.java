package com.mustafa.service;

import com.mustafa.domain.OtelOzellik;
import com.mustafa.repository.OtelOzellikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtelOzellikService {
    private final OtelOzellikRepository otelOzellikRepository;


    public OtelOzellik saveOtelOzellik(String id, String otelId) {
        OtelOzellik otelOzellik = OtelOzellik.builder()
                .otelId(otelId)
                .ozellikId(id)
                .build();
        return otelOzellikRepository.save(otelOzellik);
    }
}
