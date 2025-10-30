package com.example.restdemo.exception;

public class UserNameAlreadyExistsException extends RuntimeException {

    public UserNameAlreadyExistsException(String str) {
        super(str);
    }
}
