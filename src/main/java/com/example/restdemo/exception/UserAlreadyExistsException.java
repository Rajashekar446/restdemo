package com.example.restdemo.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String str) {
        super(str);
    }
}
