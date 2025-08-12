package com.example.scheduler.comment.controller;

import com.example.scheduler.comment.dto.CommentResponse;
import com.example.scheduler.comment.dto.CreateCommentRequest;
import com.example.scheduler.comment.dto.UpdateCommentRequest;
import com.example.scheduler.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequest request
            ) {
        CommentResponse comment = commentService.createComment(scheduleId, request);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // 전체 댓글 조회
    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponse>> findAll() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    // 개별 댓글 조회
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommentRequest request
    ) {
        return new ResponseEntity<>(commentService.update(id, request), HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
