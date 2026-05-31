package com.apartix.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class LoginResponse {

    private Integer id;
    private Integer siteId;
    private String name;
    private String email;
    private String role;
    private String apartmentNo;

    private Timestamp createdAt;

    private Integer moveInMonth;
}