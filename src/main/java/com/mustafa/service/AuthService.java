package com.mustafa.service;

import com.mustafa.domain.Auth;
import com.mustafa.dto.request.RegisterRequestDto;
import com.mustafa.repository.AuthRepository;
import com.mustafa.utility.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public Boolean register(RegisterRequestDto dto) {
        Auth auth = Auth.builder()
                .id(UUID.randomUUID())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .createAt(System.currentTimeMillis())
                .updateAt(System.currentTimeMillis())
                .state(State.PENDING)
                .build();
        authRepository.save(auth);
        return true;
    }
}
