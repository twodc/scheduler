package com.example.scheduler.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateScheduleRequest(

        @NotBlank(message = "일정 제목은 필수 입력사항입니다.")
        @Size(max = 30, message = "일정 제목은 최대 30자까지만 입력이 가능합니다.")
        String title,

        @NotBlank(message = "일정 내용은 필수 입력사항입니다.")
        @Size(max = 100, message = "일정 내용은 최대 100자까지만 입력이 가능합니다.")
        String content,

        @NotNull(message = "유저ID는 필수 입력사항입니다.")
        Long userId
) {}
