package com.example.scheduler.dto;

import com.example.scheduler.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponse {

    private final Long id;
    private final String username;
    private final String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUsername(),
                user.getEmail(), user.getCreatedAt(),user.getModifiedAt());
    }
}
