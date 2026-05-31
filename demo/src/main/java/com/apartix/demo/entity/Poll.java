package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "polls")
@Getter
@Setter
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "site_id")
    private Integer siteId;

    private String title;

    private String description;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;
}