package com.mustafa.service;

import com.mustafa.domain.UserFavoriOtel;
import com.mustafa.repository.UserFavoriOtelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFavoriOtelService {
    private final UserFavoriOtelRepository userFavoriOtelRepository;

    public void save(UserFavoriOtel userFavoriOtel) {
        userFavoriOtelRepository.save(userFavoriOtel);
    }

    public List<UserFavoriOtel> findByUserId(String id) {
        return userFavoriOtelRepository.findByUserId(id);
    }
}
