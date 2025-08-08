package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleResponse;
import com.example.scheduler.dto.UpdateScheduleRequest;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse createSchedule(String title, String content, User user) {
        Schedule schedule = new Schedule(title, content, user);
        Schedule createdSchedule = scheduleRepository.save(schedule);
        return ScheduleResponse.from(createdSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public ScheduleResponse findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return ScheduleResponse.from(findSchedule);
    }

    @Transactional
    public ScheduleResponse update(Long id, UpdateScheduleRequest request) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.updateSchedule(request.title(), request.content());
        return ScheduleResponse.from(findSchedule);
    }

    @Transactional
    public void delete(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}
