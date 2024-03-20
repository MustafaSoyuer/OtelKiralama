package com.mustafa.controller;

import com.mustafa.dto.request.SaveOzellikRequestDto;
import com.mustafa.dto.response.GetAllParentsResponseDto;
import com.mustafa.service.OzellikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mustafa.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(OZELLIK)
public class OzellikController {
    private final OzellikService ozellikService;

    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<String> saveOzellik(@RequestBody SaveOzellikRequestDto dto) {
        return ResponseEntity.ok(ozellikService.saveOzellik(dto));
    }

    @GetMapping(GET_ALL_PARENTS)
    public ResponseEntity<List<GetAllParentsResponseDto>> getAllParents() {
        return ResponseEntity.ok(ozellikService.getAllParents());
    }

}
