package com.example.scheduler.schedule.dto;

import com.example.scheduler.user.dto.AuthorResponse;
import com.example.scheduler.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record ScheduleResponse(
        Long id,
        String title,
        String content,
        AuthorResponse author,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                AuthorResponse.from(schedule.getUser()),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}


