package com.loginsystem.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class InternalUserQueryRequest {

    private String userName;

    private String password;

    @Email(message = "帳號必須是Email 格式")
    private String email;

}
