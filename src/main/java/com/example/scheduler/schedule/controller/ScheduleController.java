package com.example.scheduler.schedule.controller;

import com.example.scheduler.global.exception.CustomException;
import com.example.scheduler.global.exception.ErrorCode;
import com.example.scheduler.schedule.dto.CreateScheduleRequest;
import com.example.scheduler.schedule.dto.ScheduleResponse;
import com.example.scheduler.schedule.dto.SchedulesResponse;
import com.example.scheduler.schedule.dto.UpdateScheduleRequest;
import com.example.scheduler.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody CreateScheduleRequest request) {
        ScheduleResponse schedule = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
    }

    // 일정 목록 조회
    @GetMapping
    public ResponseEntity<Page<SchedulesResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<SchedulesResponse> schedules = scheduleService.findAllPaged(pageable);
        return ResponseEntity.ok(schedules);
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getById(@PathVariable Long id) {
        ScheduleResponse findSchedule = scheduleService.findById(id);
        return ResponseEntity.ok(findSchedule);
    }

    // 일정 수정 (제목 or 내용)
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequest request
            ) {
        if ((request.title() == null || request.title().isBlank()) &&
            (request.content() == null || request.content().isBlank())) {
            throw new CustomException(ErrorCode.INVALID_SCHEDULE_UPDATE);
        }
        ScheduleResponse updatedSchedule = scheduleService.update(id, request);
        return ResponseEntity.ok(updatedSchedule);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
