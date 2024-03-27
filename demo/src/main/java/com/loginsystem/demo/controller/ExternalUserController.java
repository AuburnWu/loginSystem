package com.loginsystem.demo.controller;

import com.loginsystem.demo.dto.ExternalUserLoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("external/user")
public class ExternalUserController {
    @PostMapping("/login")
    public String login(@RequestBody ExternalUserLoginRequest externalUserLoginRequest){
        return "";
    }

}
