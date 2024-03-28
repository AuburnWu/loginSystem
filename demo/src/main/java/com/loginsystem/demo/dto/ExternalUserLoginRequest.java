package com.loginsystem.demo.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class ExternalUserLoginRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
