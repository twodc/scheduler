package com.example.scheduler.service;

import com.example.scheduler.dto.CommentResponse;
import com.example.scheduler.dto.CreateCommentRequest;
import com.example.scheduler.entity.Comment;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.CommentRepository;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User user = userRepository.findByIdOrElseThrow(request.userId());

        Comment comment = new Comment(request.content(), schedule, user);
        commentRepository.save(comment);

        return CommentResponse.from(comment);
    }

}
