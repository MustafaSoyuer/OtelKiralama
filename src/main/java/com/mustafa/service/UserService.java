package com.mustafa.service;

import com.mustafa.domain.Otel;
import com.mustafa.domain.User;
import com.mustafa.domain.UserFavoriOtel;
import com.mustafa.dto.request.UserProfileUpdateRequestDto;
import com.mustafa.dto.response.FindByTokenResponseDto;
import com.mustafa.exception.ErrorType;
import com.mustafa.exception.UserException;
import com.mustafa.repository.OtelRepository;
import com.mustafa.repository.UserFavoriOtelRepository;
import com.mustafa.repository.UserRepository;
import com.mustafa.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final UserFavoriOtelService userFavoriOtelService;
    private final OtelRepository otelRepository;
    private final CacheManager cacheManager;


    public Boolean update(UserProfileUpdateRequestDto dto) {
        Optional<String> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        if(authId.isEmpty()){
            throw new UserException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> optionalUserProfile = userRepository.findByAuthId((authId.get()));
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

    @Cacheable(value = "find-by-token")
    public FindByTokenResponseDto findByToken(String token) {
        Optional<String> authId = jwtTokenManager.getIdFromToken(token);
        if(authId.isEmpty()){
            throw new UserException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> optionalUserProfile = userRepository.findByAuthId(String.valueOf(authId.get()));
        if (optionalUserProfile.isEmpty()){
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        User user = optionalUserProfile.get();
        return FindByTokenResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .address(user.getAddress())
                .about(user.getAbout())
                .build();
    }

    public Boolean addFavorite(String otelId, String token) {
        Optional<Otel> optionalOtel = otelRepository.findById(otelId);
        if (optionalOtel.isEmpty()){
            throw new UserException(ErrorType.OTEL_NOT_FOUND);
        }
        Optional<String> authId = jwtTokenManager.getIdFromToken(token);

        Optional<User> optionalUser = userRepository.findByAuthId((authId.get()));
        if (optionalUser.isEmpty()){
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        UserFavoriOtel userFavoriOtel = UserFavoriOtel.builder()
                .otelId(optionalOtel.get().getId())
                .userId(optionalUser.get().getId())
                .userAdi(optionalUser.get().getUsername())
                .otelAdi(optionalOtel.get().getName())
                .build();
        userFavoriOtelService.save(userFavoriOtel);
        Objects.requireNonNull(cacheManager.getCache("favori-otel")).clear();
        return true;
    }


    @Cacheable(value = "favori-otel")
    public List<UserFavoriOtel> getFavoriteOtel(String token) {
        Optional<String> authId = jwtTokenManager.getIdFromToken(token);
        if(authId.isEmpty()){
            throw new UserException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> optionalUserProfile = userRepository.findByAuthId((authId.get()));
        if (optionalUserProfile.isEmpty()){
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        return userFavoriOtelService.findByUserId(optionalUserProfile.get().getId());
    }




}
