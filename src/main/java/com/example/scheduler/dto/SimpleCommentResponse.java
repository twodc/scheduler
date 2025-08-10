package com.example.scheduler.dto;

import com.example.scheduler.entity.Comment;

public record SimpleCommentResponse(
        Long id,
        String content
) {
    public static SimpleCommentResponse from(Comment comment) {
        return new SimpleCommentResponse(
                comment.getId(),
                comment.getContent()
        );
    }
}
