package com.example.scheduler.schedule.dto;

import com.example.scheduler.comment.dto.SimpleCommentResponse;
import com.example.scheduler.user.dto.AuthorResponse;
import com.example.scheduler.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
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


