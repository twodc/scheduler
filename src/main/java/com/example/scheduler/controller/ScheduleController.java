package com.example.scheduler.controller;

import com.example.scheduler.dto.CreateScheduleRequest;
import com.example.scheduler.dto.ScheduleResponse;
import com.example.scheduler.dto.UpdateScheduleRequest;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.UserRepository;
import com.example.scheduler.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final UserRepository userRepository;
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody CreateScheduleRequest request) {
        User user = userRepository.findByIdOrElseThrow(request.userId());
        ScheduleResponse schedule = scheduleService.createSchedule(request.title(), request.content(), user);
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    // 일정 목록 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> findAll() {
        List<ScheduleResponse> schedules = scheduleService.findAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findById(@PathVariable Long id) {
        ScheduleResponse findSchedule = scheduleService.findById(id);
        return new ResponseEntity<>(findSchedule, HttpStatus.OK);
    }

    // 일정 수정 (제목 or 내용)
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequest request
            ) {
        if ((request.title() == null || request.title().isBlank()) &&
            (request.content() == null || request.content().isBlank())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 항목을 하나 이상 입력해주세요!");
        }
        ScheduleResponse updatedSchedule = scheduleService.update(id, request);
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
