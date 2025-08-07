package com.example.scheduler.dto;

import com.example.scheduler.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Author author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(), schedule.getTitle(), schedule.getContent(),
                Author.from(schedule.getUser()), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

}


