package com.example.restdemo.controller;

import com.example.restdemo.dto.LoginRequestDto;
import com.example.restdemo.dto.LoginResponseDto;
import com.example.restdemo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto);
        LoginResponseDto loginResponseDto = new LoginResponseDto(token, "Login successful");
        return ResponseEntity.ok(loginResponseDto);
    }
}
