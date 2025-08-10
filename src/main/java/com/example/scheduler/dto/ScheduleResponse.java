package com.example.scheduler.dto;

import com.example.scheduler.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public record ScheduleResponse(
        Long id,
        String title,
        String content,
        AuthorResponse author,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        List<SimpleCommentResponse> comments
) {
    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                AuthorResponse.from(schedule.getUser()),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                schedule.getComments().stream()
                        .map(SimpleCommentResponse::from)
                        .toList());
    }
}


