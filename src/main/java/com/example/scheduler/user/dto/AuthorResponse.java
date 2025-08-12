package com.example.scheduler.user.dto;

import com.example.scheduler.user.entity.User;

/**
 * AuthorResponse DTO
 * <p>
 * 이 클래스는 일정에서 작성자 정보를 표현하기 위한 데이터 전송 객체입니다.
 * User 엔티티에서 필요한 필드만 추출하여 응답에 포함합니다.
 */
public record AuthorResponse(Long id, String username) {
    public static AuthorResponse from(User user) {
        return new AuthorResponse(user.getId(), user.getUsername());
    }
}
