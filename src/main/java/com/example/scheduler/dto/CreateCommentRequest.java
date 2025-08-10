package com.example.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentRequest(

        @NotBlank(message = "댓글 내용은 필수 입력사항입니다.")
        @Size(max = 50, message = "댓글 내용은 최대 50자까지만 입력이 가능합니다.")
        String content,

        @NotNull(message = "유저ID는 필수 입력사항입니다.")
        Long userId
) {}
