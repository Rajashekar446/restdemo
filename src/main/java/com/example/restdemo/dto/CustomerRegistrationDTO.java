package com.example.restdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRegistrationDTO extends UserRegistrationDTO {
    @NotBlank
    @Size(max = 100, message = "should not be null")
    private String fullName;

    @NotBlank
    @Size(max = 200, message = "should not be null")
    private String address;

    @Size(max = 100)
    private String deliveryPreferences;

    @Size(max = 100)
    private String landmark;

}
