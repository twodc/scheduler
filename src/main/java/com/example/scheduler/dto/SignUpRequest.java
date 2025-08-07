package com.example.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    private final String username;
    private final String email;
    private final String password;

}
