package com.example.scheduler.dto;

import com.example.scheduler.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        AuthorResponse author,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(), comment.getContent(),
                AuthorResponse.from(comment.getUser()),
                comment.getCreatedAt(), comment.getModifiedAt());
    }
}
