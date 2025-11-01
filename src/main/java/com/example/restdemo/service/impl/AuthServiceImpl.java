package com.example.restdemo.service.impl;

import com.example.restdemo.domain.User;
import com.example.restdemo.dto.LoginRequestDto;
import com.example.restdemo.exception.InvalidCredentialsException;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.AuthService;
import com.example.restdemo.security.JwtUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByUserName(loginRequestDto.getUserNameOrEmail())
                .or(()->userRepository.findByEmail(loginRequestDto.getUserNameOrEmail()))
                .orElseThrow(()-> new AuthenticationException("Invalid Credentials") {
                });

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPasswordHash()))
        {
            throw new InvalidCredentialsException("Invalid Credentials") ;
        }
        return JwtUtil.generateToken(user);
    }
}
