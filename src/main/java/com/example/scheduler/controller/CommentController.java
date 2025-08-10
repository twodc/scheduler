package com.example.scheduler.controller;

import com.example.scheduler.dto.CommentResponse;
import com.example.scheduler.dto.CreateCommentRequest;
import com.example.scheduler.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequest request
            ) {
        CommentResponse comment = commentService.createComment(scheduleId, request);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

}
