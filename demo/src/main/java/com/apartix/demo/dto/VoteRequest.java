package com.apartix.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {

    private Integer userId;
    private Integer optionId;

}