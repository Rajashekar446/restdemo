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
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, unique = true )
    private User user;

    @Column(nullable = false, length = 100)
    private String businessName;

    @Column(nullable = false, length = 300)
    private String businessAddress;

    @Column(length = 20)
    private String gstin;

    @Column(length = 20)
    private String bankAccount;

    @Column(length = 30)
    private String upiId;

}
