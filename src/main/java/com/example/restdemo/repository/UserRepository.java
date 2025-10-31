package com.example.restdemo.repository;

import com.example.restdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {



    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);
}
