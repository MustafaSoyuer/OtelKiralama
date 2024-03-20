package com.mustafa.config;

import com.mustafa.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenManager jwtTokenManager;
    @Autowired
    private JwtUserDetail userDetail;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        log.info("Gelen token: " + authHeader);
        log.info("Tüm istekler buraya düşecek ve burada auth kontrol yapılacak.");
        if(Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
            log.info("Token: " + token);

            Optional<String> authId = jwtTokenManager.validateToken(token);

            if(authId.isPresent()) {
                UserDetails userDetails = userDetail.getUserByAuthId(authId.get());

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println("Authentication token : " + authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
