package com.example.restdemo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, unique = true )
    private User user;

    @Column(length = 50, nullable = false)
    private String fullName;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 50)
    private String deliveryPreferences;

    @Column(length = 50)
    private String landmark;

}
