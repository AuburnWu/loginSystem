package com.loginsystem.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ExternalUserQueryRequest {

    private String userName;

    private String password;

    private String email;

    private String nationalIdNo;

    private String unifiedBusinessNo;
}
