package com.example.restdemo.domain;

import lombok.Data;

@Data
public class ErrorResponseDTO {

    private String message;

    private String code;

    private String error;

}
