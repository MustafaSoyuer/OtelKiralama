package com.mustafa.service;

import com.mustafa.domain.User;
import com.mustafa.dto.request.UserProfileUpdateRequestDto;
import com.mustafa.exception.ErrorType;
import com.mustafa.exception.UserException;
import com.mustafa.repository.UserRepository;
import com.mustafa.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;


    public Boolean update(UserProfileUpdateRequestDto dto) {
        Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        if(authId.isEmpty()){
            throw new UserException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> optionalUserProfile = userRepository.findByAuthId(String.valueOf(authId.get()));
        if (optionalUserProfile.isEmpty()){
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        User user = optionalUserProfile.get();
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAvatar(dto.getAvatar());
        user.setAddress(dto.getAddress());
        user.setUpdateAt(System.currentTimeMillis());
        userRepository.save(user);
        return true;

    }
}
