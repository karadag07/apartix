package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "announcements")
@Getter
@Setter
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "site_id")
    private Integer siteId;

    @Column(name = "admin_id")
    private Integer adminId;

    private String title;

    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;
}