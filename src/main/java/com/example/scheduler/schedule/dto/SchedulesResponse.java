package com.example.scheduler.schedule.dto;

import com.example.scheduler.schedule.entity.Schedule;
import com.example.scheduler.user.dto.AuthorResponse;

import java.time.LocalDateTime;

public record SchedulesResponse(
        String title,
        String content,
        int commentCount,
        AuthorResponse author,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static SchedulesResponse from(Schedule schedule) {
        return new SchedulesResponse(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getComments().size(),
                AuthorResponse.from(schedule.getUser()),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
