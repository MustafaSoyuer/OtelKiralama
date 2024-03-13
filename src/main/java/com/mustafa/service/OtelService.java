package com.mustafa.service;

import com.mustafa.domain.Otel;
import com.mustafa.dto.request.SaveOtelRequestDto;
import com.mustafa.dto.response.OtelFindByIdResponseDto;
import com.mustafa.exception.ErrorType;
import com.mustafa.exception.OtelException;
import com.mustafa.exception.UserException;
import com.mustafa.repository.OtelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OtelService {
    private final OtelRepository otelRepository;
    private final CacheManager cacheManager;


    public String saveOtel(SaveOtelRequestDto dto) {
        Otel otel = Otel.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .lon(dto.getLon())
                .lat(dto.getLat())
                .totalPoint(dto.getTotalPoint())
                .girisSaati(dto.getGirisSaati())
                .cikisSaati(dto.getCikisSaati())
                .genelAciklama(dto.getGenelAciklama())
                .createAt(System.currentTimeMillis())
                .updateAt(System.currentTimeMillis())
                .build();
        otelRepository.save(otel);
        Objects.requireNonNull(cacheManager.getCache("find-all")).clear();
        Objects.requireNonNull(cacheManager.getCache("find-by-search")).clear();
        return otel.getId();
    }

    @Cacheable(value = "find-by-id", key = "#id.toUpperCase()")
    public OtelFindByIdResponseDto findById(String id) {
        try {
            Thread.sleep(3000);
            Otel otel = otelRepository.findById(id).orElseThrow(() -> new OtelException(ErrorType.OTEL_NOT_FOUND));
            return OtelFindByIdResponseDto.builder()
                    .id(otel.getId())
                    .name(otel.getName())
                    .address(otel.getAddress())
                    .phone(otel.getPhone())
                    .email(otel.getEmail())
                    .lon(otel.getLon())
                    .lat(otel.getLat())
                    .totalPoint(otel.getTotalPoint())
                    .girisSaati(otel.getGirisSaati())
                    .cikisSaati(otel.getCikisSaati())
                    .genelAciklama(otel.getGenelAciklama())
                    .build();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Cacheable(value = "find-all")
    public List<Otel> findAll() {
        try {
            Thread.sleep(3000);
            return otelRepository.findAllByOrderByTotalPointDesc();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Cacheable(value = "find-by-search", key = "#search.toUpperCase()")
    public List<Otel> findBySearch(String search) {
        return otelRepository.findByGenelAciklamaContainingIgnoreCase(search);
    }
}
