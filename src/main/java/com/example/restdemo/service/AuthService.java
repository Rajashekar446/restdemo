package com.example.restdemo.service;

import com.example.restdemo.dto.LoginRequestDto;

public interface AuthService {


    String login(LoginRequestDto loginRequestDto);
}
