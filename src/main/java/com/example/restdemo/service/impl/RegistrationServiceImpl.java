package com.example.restdemo.service.impl;

import com.example.restdemo.domain.Customer;
import com.example.restdemo.domain.User;
import com.example.restdemo.domain.UserRole;
import com.example.restdemo.dto.CustomerRegistrationDTO;
import com.example.restdemo.dto.VendorRegistrationDTO;
import com.example.restdemo.exception.UserNameAlreadyExistsException;
import com.example.restdemo.repository.CustomerRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.repository.VendorRepository;
import com.example.restdemo.service.RegistrationService;
import com.example.restdemo.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private UserRepository userRepository;

    private VendorRepository vendorRepository;

    private CustomerRepository customerRepository;

    public RegistrationServiceImpl(UserRepository userRepository, VendorRepository vendorRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void registerVendor(VendorRegistrationDTO vendorRegistrationDTO) {
        User user = new User();
        //user.s


    }

    @Override
    public void customerRegistration(CustomerRegistrationDTO customerRegistrationDTO) {

        User user = new User();
        Customer customer = new Customer();
        if (userRepository.findByUserName(customerRegistrationDTO.getUserName()).isPresent()) {
            throw new UserNameAlreadyExistsException("USERNAME ALREADY EXISTS");
        }
        user.setUserName(customerRegistrationDTO.getUserName());
        user.setEmail(customerRegistrationDTO.getEmail());
        user.setPasswordHash(PasswordUtil.encode(customerRegistrationDTO.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        user.setRegisteredAt(LocalDateTime.now());
        user.setPhone(customerRegistrationDTO.getPhone());
        customer.setFullName(customerRegistrationDTO.getFullName());
        customer.setAddress(customerRegistrationDTO.getAddress());
        customer.setLandmark(customerRegistrationDTO.getLandmark());
        customer.setDeliveryPreferences(customerRegistrationDTO.getDeliveryPreferences());
        customer.setUser(user);
        user.setCustomer(customer);
        userRepository.save(user);
    }
}
