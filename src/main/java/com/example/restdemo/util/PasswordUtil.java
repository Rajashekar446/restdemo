package com.example.restdemo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();
    public static String encode(CharSequence rawPassword) {
        return ENCODER.encode(rawPassword);
    }
}
