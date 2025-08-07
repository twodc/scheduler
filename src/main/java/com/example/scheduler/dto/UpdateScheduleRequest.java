package com.example.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateScheduleRequest {
    private final String title;
    private final String content;
}
