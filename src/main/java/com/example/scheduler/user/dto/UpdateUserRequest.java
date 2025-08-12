package com.example.scheduler.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotBlank(message = "변경할 유저명을 입력해주세요!")
        @Size(max = 20, message = "유저명은 최대 20자까지 입력이 가능합니다.")
        String username
) {}
