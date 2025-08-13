package com.example.scheduler.user.repository;

import com.example.scheduler.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다. ID: " + id));
    }

    User findByEmail(String email);
}
