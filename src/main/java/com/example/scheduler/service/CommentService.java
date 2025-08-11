package com.example.scheduler.service;

import com.example.scheduler.dto.*;
import com.example.scheduler.entity.Comment;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.CommentRepository;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Comment createdComment = commentRepository.save(comment);
        return CommentResponse.from(createdComment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public CommentResponse findById(Long id) {
        Comment comment = commentRepository.findByIdOrElseThrow(id);
        return CommentResponse.from(comment);
    }

    @Transactional
    public CommentResponse update(Long id, @Valid UpdateCommentRequest request) {
        Comment comment = commentRepository.findByIdOrElseThrow(id);
        comment.updateContent(request.content());
        return CommentResponse.from(comment);
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findByIdOrElseThrow(id);
        commentRepository.delete(comment);
    }
}
