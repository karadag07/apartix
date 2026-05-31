package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "announcement_comments")
@Getter
@Setter
public class AnnouncementComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "announcement_id")
    private Integer announcementId;

    @Column(name = "user_id")
    private Integer userId;

    private String comment;

    @Column(name = "created_at")
    private Timestamp createdAt;
}