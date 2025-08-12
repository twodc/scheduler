package com.example.scheduler.comment.dto;

import com.example.scheduler.user.dto.AuthorResponse;
import com.example.scheduler.comment.entity.Comment;

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
