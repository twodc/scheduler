package com.example.scheduler.user.repository;

import com.example.scheduler.global.exception.CustomException;
import com.example.scheduler.global.exception.ErrorCode;
import com.example.scheduler.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
