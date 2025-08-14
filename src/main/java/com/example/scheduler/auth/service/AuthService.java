package com.example.scheduler.auth.service;

import com.example.scheduler.auth.dto.LoginRequest;
import com.example.scheduler.auth.dto.LoginResponse;
import com.example.scheduler.auth.security.PasswordEncoder;
import com.example.scheduler.global.exception.CustomException;
import com.example.scheduler.global.exception.ErrorCode;
import com.example.scheduler.user.entity.User;
import com.example.scheduler.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email());
        if (user == null || !passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.LOGIN_FAILED);
        }
        return new LoginResponse(user.getId());
    }
}
