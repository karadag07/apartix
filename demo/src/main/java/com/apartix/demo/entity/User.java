package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "site_id")
    private Integer siteId;

    private String name;

    private String email;

    private String password;

    private String role;

    @Column(name = "apartment_no")
    private String apartmentNo;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "move_in_month")
private Integer moveInMonth;
}