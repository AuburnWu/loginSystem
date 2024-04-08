package com.loginsystem.demo.controller;

import com.loginsystem.demo.dto.ExternalUserCreateRequest;
import com.loginsystem.demo.dto.UserLoginRequest;
import com.loginsystem.demo.dto.ExternalUserQueryRequest;
import com.loginsystem.demo.dto.UserResponse;
import com.loginsystem.demo.model.entity.ExternalUser;
import com.loginsystem.demo.service.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("external/user")
public class ExternalUserController {

    @Autowired
    ExternalUserService externalUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ExternalUserCreateRequest externalUserCreateRequest) {
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(externalUserCreateRequest.getUserName());
        externalUser.setEmail(externalUserCreateRequest.getEmail());
        externalUser.setNationalIdNo(externalUserCreateRequest.getNationalIdNo());
        externalUser.setUnifiedBusinessNo(externalUserCreateRequest.getUnifiedBusinessNo());

        UserResponse<Void> response = externalUserService.createUser(externalUser);

        return ResponseEntity.status(HttpStatus.OK).body(response.getOperateStatus().getMessage());

    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse<ExternalUser>> login(@RequestBody UserLoginRequest userLoginRequest) {
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(userLoginRequest.getUserName());
        externalUser.setPassword(userLoginRequest.getPassword());

        UserResponse<ExternalUser> response = externalUserService.loginUser(externalUser);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/select")
    public ResponseEntity<UserResponse<List<ExternalUser>>> selectUserByName(@RequestBody ExternalUserQueryRequest externalUserQueryRequest) {
        ExternalUser externalUser = new ExternalUser();
        UserResponse<List<ExternalUser>> response = externalUserService.queryUsersByName(externalUserQueryRequest.getUserName());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse<Void>> update(@RequestBody ExternalUserQueryRequest externalUserQueryRequest) {
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(externalUserQueryRequest.getUserName());
        externalUser.setEmail(externalUserQueryRequest.getEmail());
        externalUser.setPassword(externalUserQueryRequest.getPassword());
        externalUser.setNationalIdNo(externalUserQueryRequest.getNationalIdNo());
        externalUser.setUnifiedBusinessNo(externalUserQueryRequest.getUnifiedBusinessNo());

        UserResponse<Void> response = externalUserService.updateUser(externalUser);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserResponse<Void>> delete(@RequestBody ExternalUserQueryRequest externalUserQueryRequest) {
        String UserName = externalUserQueryRequest.getUserName();

        UserResponse<Void> response = externalUserService.deleteUser(UserName);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
