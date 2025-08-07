package com.example.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequest {

    private final String title;
    private final String content;
    private final Long userId;

}
