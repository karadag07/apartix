package com.apartix.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "dues")
@Getter
@Setter
public class Due {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "site_id")
    private Integer siteId;

    @Column(name = "user_id")
    private Integer userId;

    private Integer month;

    private Integer year;

    private BigDecimal amount;

    @Column(name = "late_fee")
    private BigDecimal lateFee;

    @Column(name = "remaining_amount")
    private BigDecimal remainingAmount;

    private String status;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "paid_at")
    private Timestamp paidAt;

    @Column(name = "last_updated")
    private Date lastUpdated;
}