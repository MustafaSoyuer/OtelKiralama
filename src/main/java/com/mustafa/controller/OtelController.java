package com.mustafa.controller;

import com.mustafa.domain.Otel;
import com.mustafa.dto.request.SaveOtelRequestDto;
import com.mustafa.dto.response.OtelFindByIdResponseDto;
import com.mustafa.service.OtelService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.mustafa.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(OTEL)
public class OtelController {
    private final OtelService otelService;

    @PostMapping(SAVE)
    public ResponseEntity<String> saveOtel(@RequestBody SaveOtelRequestDto dto) {
        return ResponseEntity.ok(otelService.saveOtel(dto));
    }


    @GetMapping(FIND_BY_ID)
    public ResponseEntity<OtelFindByIdResponseDto> findById(String id) {
            return ResponseEntity.ok(otelService.findById(id));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Otel>> findAll() {
        return ResponseEntity.ok(otelService.findAll());
    }

    @GetMapping(FIND_SEARCH)
    public ResponseEntity<List<Otel>> findBySearch(String search) {
        return ResponseEntity.ok(otelService.findBySearch(search));
    }





}

