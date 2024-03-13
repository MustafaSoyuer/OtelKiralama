package com.mustafa.controller;

import com.mustafa.domain.UserFavoriOtel;
import com.mustafa.dto.request.UserProfileUpdateRequestDto;
import com.mustafa.dto.response.FindByTokenResponseDto;
import com.mustafa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mustafa.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody UserProfileUpdateRequestDto dto){
        return ResponseEntity.ok(userService.update(dto));
    }

    @GetMapping(FIND_BY_TOKEN)
    public ResponseEntity<FindByTokenResponseDto> findByToken(String token){
        return ResponseEntity.ok(userService.findByToken(token));
    }


    @PostMapping(ADD_FAVORITE)
    public ResponseEntity<Boolean> addFavorite(@RequestBody String otelId, String token){
        return ResponseEntity.ok(userService.addFavorite(otelId, token));
    }

    @GetMapping(FAVORI_OTELS)
    public ResponseEntity<List<UserFavoriOtel>> getFavoriteOtel(String token){
        return ResponseEntity.ok(userService.getFavoriteOtel(token));
    }


}
