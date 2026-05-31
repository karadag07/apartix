package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "poll_options")
@Getter
@Setter
public class PollOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "poll_id")
    private Integer pollId;

    @Column(name = "option_text")
    private String optionText;

    @Column(name = "candidate_user_id")
    private Integer candidateUserId;
}