package com.mustafa.service;

import com.mustafa.domain.Auth;
import com.mustafa.domain.User;
import com.mustafa.dto.request.ActivateStatusRequestDto;
import com.mustafa.dto.request.LoginRequestDto;
import com.mustafa.dto.request.RegisterRequestDto;
import com.mustafa.dto.response.RegisterResponseDto;
import com.mustafa.exception.AuthException;
import com.mustafa.exception.ErrorType;
import com.mustafa.mapper.AuthMapper;
import com.mustafa.repository.AuthRepository;
import com.mustafa.repository.UserRepository;
import com.mustafa.utility.CodeGenerator;
import com.mustafa.utility.JwtTokenManager;
import com.mustafa.utility.enums.ERole;
import com.mustafa.utility.enums.EState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final UserRepository userRepository;
    private final MailService mailService;


    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        authRepository.save(auth);
        if (dto.getUsername().equals("admin") && dto.getPassword().equals("admin*")) {
            auth.setRole(ERole.ADMIN);
            auth.setState(EState.ACTIVE);
            authRepository.save(auth);
        }
        User user = User.builder()
                .authId(auth.getId())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .build();
        mailService.sendMail(dto.getEmail(), auth.getActivationCode());
        userRepository.save(user);
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if(authOptional.isEmpty()){
            throw new AuthException(ErrorType.LOGIN_ERROR);
        }
        if(authOptional.get().getState().equals(EState.ACTIVE)){
            return jwtTokenManager.createToken(authOptional.get().getId())
                    .orElseThrow(() -> {throw new AuthException(ErrorType.TOKEN_NOT_CREATED);
                    });
        } else {
            throw new AuthException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
    }


    public Boolean activateStatus(String email,String activationCode) {
        Optional<Auth> optionalAuth = authRepository.findByEmail(email);
        if(optionalAuth.isEmpty()){
            throw new AuthException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalAuth.get().getActivationCode().equals(activationCode)){
            optionalAuth.get().setState(EState.ACTIVE);

            Optional<User> optionalUser = userRepository.findByAuthId(optionalAuth.get().getId());

            if (optionalUser.isEmpty()) {
                throw new AuthException(ErrorType.USER_NOT_FOUND);
            }

            optionalUser.get().setState(EState.ACTIVE);
            authRepository.save(optionalAuth.get());
            userRepository.save(optionalUser.get());
            return true;
        } else {
            throw new AuthException(ErrorType.ACTIVATION_CODE_ERROR);
        }
    }
}
