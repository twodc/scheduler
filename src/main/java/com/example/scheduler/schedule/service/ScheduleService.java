package com.example.scheduler.schedule.service;

import com.example.scheduler.schedule.dto.ScheduleResponse;
import com.example.scheduler.schedule.dto.SchedulesResponse;
import com.example.scheduler.schedule.dto.UpdateScheduleRequest;
import com.example.scheduler.schedule.entity.Schedule;
import com.example.scheduler.user.entity.User;
import com.example.scheduler.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<SchedulesResponse> findAllPaged(Pageable pageable) {
        return scheduleRepository.findAll(pageable).map(SchedulesResponse::from);
    }

    @Transactional(readOnly = true)
    public ScheduleResponse findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return ScheduleResponse.from(findSchedule);
    }

    @Transactional
    public ScheduleResponse update(Long id, UpdateScheduleRequest request) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if (request.title() != null) {
            findSchedule.updateTitle(request.title());
        }
        if (request.content() != null) {
            findSchedule.updateContent(request.content());
        }

        return ScheduleResponse.from(findSchedule);
    }

    @Transactional
    public void delete(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}
