package com.example.restdemo.controller;

import com.example.restdemo.dto.CustomerRegistrationDTO;
import com.example.restdemo.dto.VendorRegistrationDTO;
import com.example.restdemo.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/vendor")
    void registerVendor(@Valid VendorRegistrationDTO vendorRegistrationDTO) {
        registrationService.registerVendor(vendorRegistrationDTO);

    }

    @PostMapping("/customer")
    void  customerRegistration(@Valid @RequestBody CustomerRegistrationDTO customerRegistrationDTO) {
        registrationService.customerRegistration(customerRegistrationDTO);
    }

}
