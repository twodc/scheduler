package com.example.scheduler.controller;

import com.example.scheduler.dto.*;
import com.example.scheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest request) {
        UserResponse userResponse = userService.signUp(request.getUsername(), request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    // 로그인

    // 모든 유저 조회
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    // 개별 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    // 유저 이름 변경
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        return new ResponseEntity<>(userService.update(id, request), HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
