package com.loginsystem.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ExternalUserQueryRequest {

    private String userName;

    private String password;

    @Email(message = "帳號必須是Email 格式")
    private String email;

    private String nationalIdNo;

    private String unifiedBusinessNo;
}
