package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "sites")
@Getter
@Setter
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private String phone;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "join_code")
private String joinCode;
}