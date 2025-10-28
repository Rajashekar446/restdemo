package com.example.restdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VendorRegistrationDTO extends UserRegistrationDTO {
    @NotBlank
    @Size(max = 100, message = "should not be null")
    private String businessName;

    @NotBlank
    @Size(max = 200, message = "should not be null")
    private String businessAddress;

    @Pattern(regexp = "^[0-9A-Z]{15}$", message = "Invalid GSTIN format")
    private String gstin;

    @Size(min = 6, max = 30)
    private String bankAccount;

    @Size(max = 50)
    private String upiId;

}
