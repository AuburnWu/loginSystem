package com.loginsystem.demo.dto;

import lombok.Data;

@Data
public class ExternalUserLoginRequest {
    private String userName;
    private String email;
    private String nationalIdNo;
    private String unifiedBusinessNo;
}
