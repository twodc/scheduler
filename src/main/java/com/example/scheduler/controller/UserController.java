package com.example.scheduler.controller;

import com.example.scheduler.dto.*;
import com.example.scheduler.entity.User;
import com.example.scheduler.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        UserResponse userResponse = userService.signUp(request.username(), request.email(), request.password());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request,
                      HttpServletRequest httpRequest) {
        User user = userService.login(request.email(), request.password());
        HttpSession session = httpRequest.getSession();
        session.setAttribute("LOGIN_USER", user.getId());
        return ResponseEntity.ok("로그인 되었습니다.");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

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

    // 유저명 변경
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request
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
