package com.apartix.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnnouncementRequest {

    private Integer siteId;
    private Integer adminId;
    private String title;
    private String content;

}