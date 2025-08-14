package com.example.scheduler.user.service;

import com.example.scheduler.global.config.PasswordEncoder;
import com.example.scheduler.global.exception.CustomException;
import com.example.scheduler.global.exception.ErrorCode;
import com.example.scheduler.user.dto.LoginRequest;
import com.example.scheduler.user.dto.SignUpRequest;
import com.example.scheduler.user.dto.UpdateUserRequest;
import com.example.scheduler.user.dto.UserResponse;
import com.example.scheduler.user.entity.User;
import com.example.scheduler.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.username(), request.email(), encodedPassword);
        User savedUser = userRepository.save(user);
        return UserResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(UserResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return UserResponse.from(findUser);
    }

    @Transactional
    public UserResponse update(Long id, UpdateUserRequest request) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        findUser.updateUsername(request.username());
        return UserResponse.from(findUser);
    }

    @Transactional
    public void delete(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }

    @Transactional(readOnly = true)
    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email());
        if (user == null || !passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.LOGIN_FAILED);
        }
        return user;
    }
}
