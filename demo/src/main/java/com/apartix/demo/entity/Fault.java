package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "faults")
@Getter
@Setter
public class Fault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "site_id")
    private Integer siteId;

    @Column(name = "user_id")
    private Integer userId;

    private String title;

    private String description;

    private String status;

    @Column(name = "created_at")
    private Timestamp createdAt;
}