package com.mustafa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public JwtAuthFilter getJwtAuthFilter(){

        return new JwtAuthFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.authorizeHttpRequests(req->
                req.requestMatchers(
                        "/swagger-ui/**",
                                     "/v3/api-docs/**"

                        ).permitAll()
                            .requestMatchers("/dev/v1/auth/**").permitAll()
                            .requestMatchers("/dev/v1/user/**").permitAll()
 //                            .requestMatchers("/dev/v1/auth/login/**").permitAll()
//                            .requestMatchers("/dev/v1/user/update/**").hasAuthority("ADMIN")
                                .anyRequest()
                                    .authenticated()
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        log.info("*****  Tüm istekler buradan geçecek. *****");
        httpSecurity.addFilterBefore(getJwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();


    }
}
