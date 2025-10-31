package com.example.restdemo.mapper;

import com.example.restdemo.domain.Customer;
import com.example.restdemo.domain.User;
import com.example.restdemo.domain.UserRole;
import com.example.restdemo.dto.CustomerRegistrationDTO;

import java.time.LocalDateTime;

public class CustomerMapper {

    public static User toUserEntity(CustomerRegistrationDTO dto, String hashedPassword) {
        return User.builder()
                .userName(dto.getUserName())
                .passwordHash(hashedPassword)
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .role(UserRole.CUSTOMER)
                .registeredAt(LocalDateTime.now())
                .build();
    }

    public static Customer toCustomerEntity(CustomerRegistrationDTO dto) {
        return Customer.builder()
                .address(dto.getAddress())
                .deliveryPreferences(dto.getDeliveryPreferences())
                .fullName(dto.getFullName())
                .landmark(dto.getLandmark())
                .build();

    }
}
