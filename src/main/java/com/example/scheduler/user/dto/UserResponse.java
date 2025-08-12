package com.example.scheduler.user.dto;

import com.example.scheduler.user.entity.User;

import java.time.LocalDateTime;

public record UserResponse(
        Long id, String username, String email,
        LocalDateTime createdAt, LocalDateTime modifiedAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(), user.getUsername(), user.getEmail(),
                user.getCreatedAt(), user.getModifiedAt());
    }
}