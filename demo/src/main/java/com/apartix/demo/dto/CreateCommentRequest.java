package com.apartix.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {

    private Integer announcementId;
    private Integer userId;
    private String comment;
}