package com.apartix.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFaultRequest {

    private Integer siteId;
    private Integer userId;
    private String title;
    private String description;

}