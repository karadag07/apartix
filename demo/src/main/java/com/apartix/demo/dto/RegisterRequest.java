package com.apartix.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String role;

    private String name;
    private String email;
    private String password;
    private String apartmentNo;

    private String siteName;
    private String siteAddress;
    private String sitePhone;

    private String joinCode;
    private Integer moveInMonth;
}