package com.example.scheduler.service;

import com.example.scheduler.dto.UpdateUserRequest;
import com.example.scheduler.dto.UserResponse;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse signUp(String username, String email, String password) {
        User user = new User(username, email, password);
        userRepository.save(user);
        return UserResponse.from(user);
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
        findUser.updateUser(request.username());
        return UserResponse.from(findUser);
    }

    @Transactional
    public void delete(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }

    @Transactional(readOnly = true)
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 틀렸습니다."));
    }
}
