package com.loginsystem.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InternalUserCreateRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
