package com.example.restdemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class UserRegistrationDTO {
    @NotBlank
    @Size(min = 4, max = 50, message = "userName should not be null")
    private String userName;

    @NotBlank
    @Size(min = 8, message = "min 8 characters length")
    private String password;

    @NotBlank
    @Email(message = "provide proper email")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "does not match pattern")
    private String phone;

}
