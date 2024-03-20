package com.mustafa.controller;

import com.mustafa.domain.Auth;
import com.mustafa.dto.request.ActivateStatusRequestDto;
import com.mustafa.dto.request.LoginRequestDto;
import com.mustafa.dto.request.RegisterRequestDto;
import com.mustafa.dto.response.RegisterResponseDto;
import com.mustafa.exception.AuthException;
import com.mustafa.exception.ErrorType;
import com.mustafa.service.AuthService;
import com.mustafa.utility.JwtTokenManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.mustafa.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @GetMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestParam String email, @RequestParam String activationCode){
        return ResponseEntity.ok(authService.activateStatus(email, activationCode));
    }

}
