package com.example.restdemo.service.impl;

import com.example.restdemo.domain.Customer;
import com.example.restdemo.domain.User;
import com.example.restdemo.dto.CustomerRegistrationDTO;
import com.example.restdemo.dto.VendorRegistrationDTO;
import com.example.restdemo.exception.UserAlreadyExistsException;
import com.example.restdemo.mapper.CustomerMapper;
import com.example.restdemo.repository.CustomerRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.repository.VendorRepository;
import com.example.restdemo.service.RegistrationService;
import com.example.restdemo.util.PasswordUtil;
import org.springframework.stereotype.Service;

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

    }

    @Override
    public void customerRegistration(CustomerRegistrationDTO customerRegistrationDTO) {

        //check if username already exists
        if (userRepository.findByUserName(customerRegistrationDTO.getUserName()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        //check if email already exists
        if (userRepository.findByEmail(customerRegistrationDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        //check for existing phone number
        if (userRepository.findByPhone(customerRegistrationDTO.getPhone()).isPresent()) {
            throw new UserAlreadyExistsException("Phone number already exists");
        }

        String hashedPassword = PasswordUtil.encode(customerRegistrationDTO.getPassword());
        User user = CustomerMapper.toUserEntity(customerRegistrationDTO, hashedPassword);
        Customer customer = CustomerMapper.toCustomerEntity(customerRegistrationDTO);

        customer.setUser(user);
        user.setCustomer(customer);
        userRepository.save(user);
    }
}
