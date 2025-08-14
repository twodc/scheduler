package com.example.scheduler.comment.repository;

import com.example.scheduler.comment.entity.Comment;
import com.example.scheduler.global.exception.CustomException;
import com.example.scheduler.global.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    List<Comment> findByScheduleId(Long scheduleId);
    
    long countByScheduleId(Long scheduleId);
}
