package com.example.scheduler.schedule.dto;

import com.example.scheduler.comment.dto.SimpleCommentResponse;
import com.example.scheduler.schedule.entity.Schedule;
import com.example.scheduler.user.dto.AuthorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ScheduleWithCommentResponse(
        Long id,
        String title,
        String content,
        AuthorResponse author,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        List<SimpleCommentResponse> comments
) {
    public static ScheduleWithCommentResponse from(Schedule schedule, List<SimpleCommentResponse> comments) {
        return new ScheduleWithCommentResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                AuthorResponse.from(schedule.getUser()),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                comments);
    }
}


