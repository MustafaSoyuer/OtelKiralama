package com.mustafa.config;


import com.mustafa.domain.Auth;
import com.mustafa.domain.User;
import com.mustafa.repository.AuthRepository;
import com.mustafa.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JwtUserDetail implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    public UserDetails getUserByAuthId(String authId) {
        Optional<User> authUser = userRepository.findOptionalByAuthId(authId);
        Optional<Auth> auth = authRepository.findById(authId);
        log.info("Auth bilgileri: " + auth.get());
        log.info("User bilgileri: " + authUser.get());
        if (authUser.isEmpty()) return null;
        if (auth.get().getRole().name().equals("ADMIN")) {

            return org.springframework.security.core.userdetails.User.builder()
                    .username(authUser.get().getUsername())
                    .password("")
                    .accountLocked(false)
                    .accountExpired(false)
                    .authorities(new SimpleGrantedAuthority("ADMIN")) //kullanıcının yetkilerini yazıyoruz.
                    .build();
        } else if (auth.get().getRole().name().equals("USER")) {

            return org.springframework.security.core.userdetails.User.builder()
                    .username(authUser.get().getUsername())
                    .password("")
                    .accountLocked(false)
                    .accountExpired(false)
                    .authorities(new SimpleGrantedAuthority("USER")) //kullanıcının yetkilerini yazıyoruz.
                    .build();

        }
        return null;
    }
}

