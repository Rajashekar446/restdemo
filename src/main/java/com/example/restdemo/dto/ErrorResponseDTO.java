package com.example.restdemo.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {

    private String message;

    private String code;

    private String error;

}
