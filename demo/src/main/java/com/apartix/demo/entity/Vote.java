package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "votes")
@Getter
@Setter
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "poll_id")
    private Integer pollId;

    @Column(name = "option_id")
    private Integer optionId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created_at")
    private Timestamp createdAt;
}