package com.example.scheduler.schedule.dto;

import com.example.scheduler.schedule.entity.Schedule;
import com.example.scheduler.user.dto.AuthorResponse;

import java.time.LocalDateTime;

public record SchedulesResponse(
        Long id,
        String title,
        String content,
        long commentCount,
        AuthorResponse author,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static SchedulesResponse from(Schedule schedule, long commentCount) {
        return new SchedulesResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                commentCount,
                AuthorResponse.from(schedule.getUser()),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
