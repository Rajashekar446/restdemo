package com.example.restdemo.service;

import com.example.restdemo.dto.CustomerRegistrationDTO;
import com.example.restdemo.dto.VendorRegistrationDTO;

public interface RegistrationService {

    void registerVendor(VendorRegistrationDTO  vendorRegistrationDTO);

    void customerRegistration(CustomerRegistrationDTO customerRegistrationDTO);


}
