package com.loginsystem.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ExternalUserCreateRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    private String nationalIdNo;
    @NotBlank
    private String unifiedBusinessNo;
}
