package com.loginsystem.demo.controller;

import com.loginsystem.demo.dto.ExternalUserCreateRequest;
import com.loginsystem.demo.dto.ExternalUserLoginRequest;
import com.loginsystem.demo.dto.ExternalUserQueryRequest;
import com.loginsystem.demo.model.entity.ExternalUser;
import com.loginsystem.demo.service.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("external/user")
public class ExternalUserController {

    @Autowired
    ExternalUserService externalUserService;

    @PostMapping("/register")
    public String register(@RequestBody ExternalUserCreateRequest externalUserCreateRequest){
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(externalUserCreateRequest.getUserName());
        externalUser.setEmail(externalUserCreateRequest.getEmail());
        externalUser.setNationalIdNo(externalUserCreateRequest.getNationalIdNo());
        externalUser.setUnifiedBusinessNo(externalUserCreateRequest.getUnifiedBusinessNo());

        if (!externalUserService.createUser(externalUser).isEmpty()){

        }

        return "";
    }

    @PostMapping("/login")
    public String login(@RequestBody ExternalUserLoginRequest externalUserLoginRequest){
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(externalUserLoginRequest.getUserName());
        externalUser.setPassword(externalUserLoginRequest.getPassword());

        externalUserService.loginUser(externalUser);

        return "";
    }

    @PostMapping("/select")
    public String selectUserByName(@RequestBody ExternalUserQueryRequest externalUserQueryRequest){
        ExternalUser externalUser = new ExternalUser();

        externalUserService.queryUserByName(externalUserQueryRequest.getUserName());

        return "";
    }

    @PutMapping("/update")
    public String update(@RequestBody ExternalUserQueryRequest externalUserQueryRequest){
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(externalUserQueryRequest.getUserName());
        externalUser.setEmail(externalUserQueryRequest.getEmail());
        externalUser.setPassword(externalUserQueryRequest.getPassword());
        externalUser.setNationalIdNo(externalUserQueryRequest.getNationalIdNo());
        externalUser.setUnifiedBusinessNo(externalUserQueryRequest.getUnifiedBusinessNo());

        externalUserService.queryUserByName(externalUserQueryRequest.getUserName());
        externalUserService.updataUser(externalUser);

        return "";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody ExternalUserQueryRequest externalUserQueryRequest){
        String UserName =  externalUserQueryRequest.getUserName();

        externalUserService.queryUserByName(UserName);
        externalUserService.deleteUser(UserName);

        return "";
    }

}
