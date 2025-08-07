package com.example.scheduler.dto;

import com.example.scheduler.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author DTO
 *
 * 이 클래스는 일정에서 작성자 정보를 표현하기 위한 데이터 전송 객체입니다.
 * User 엔티티에서 필요한 필드만 추출하여 응답에 포함합니다.
 *
 * 주요 필드:
 * - id: 작성자의 고유 식별자
 * - username: 작성자의 사용자 이름
 */
@Getter
@AllArgsConstructor
public class Author {

    private Long id;
    private String username;

    public static Author from(User user) {
        return new Author(user.getId(), user.getUsername());
    }
}
