package com.example.scheduler.schedule.repository;

import com.example.scheduler.global.exception.CustomException;
import com.example.scheduler.global.exception.ErrorCode;
import com.example.scheduler.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}
