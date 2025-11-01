package com.example.restdemo.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String userNameOrEmail;
    private String password;
}
