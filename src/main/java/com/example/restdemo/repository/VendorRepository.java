package com.example.restdemo.repository;

import com.example.restdemo.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
