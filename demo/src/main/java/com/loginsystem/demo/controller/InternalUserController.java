package com.loginsystem.demo.controller;

import com.loginsystem.demo.dto.InternalUserCreateRequest;
import com.loginsystem.demo.dto.InternalUserQueryRequest;
import com.loginsystem.demo.dto.UserLoginRequest;
import com.loginsystem.demo.dto.UserResponse;
import com.loginsystem.demo.model.entity.InternalUser;
import com.loginsystem.demo.service.InternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("internal/user")
public class InternalUserController {
    @Autowired
    InternalUserService internalUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody InternalUserCreateRequest internalUserCreateRequest) {
        InternalUser internalUser = new InternalUser();
        internalUser.setUserName(internalUserCreateRequest.getUserName());
        internalUser.setEmail(internalUserCreateRequest.getEmail());
        internalUser.setPassword(internalUserCreateRequest.getPassword());

        UserResponse<Void> response = internalUserService.createUser(internalUser);

        return ResponseEntity.status(HttpStatus.OK).body(response.getOperateStatus().getMessage());

    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse<InternalUser>> login(@RequestBody UserLoginRequest userLoginRequest) {
        InternalUser internalUser = new InternalUser();
        internalUser.setUserName(userLoginRequest.getUserName());
        internalUser.setPassword(userLoginRequest.getPassword());

        UserResponse<InternalUser> response = internalUserService.loginUser(internalUser);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/select")
    public ResponseEntity<UserResponse<List<InternalUser>>> selectUserByName(@RequestBody InternalUserQueryRequest internalUserQueryRequest) {
        InternalUser internalUser = new InternalUser();
        UserResponse<List<InternalUser>> response = internalUserService.queryUsersByName(internalUserQueryRequest.getUserName());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse<Void>> update(@RequestBody InternalUserQueryRequest internalUserQueryRequest) {
        InternalUser internalUser = new InternalUser();
        internalUser.setUserName(internalUserQueryRequest.getUserName());
        internalUser.setEmail(internalUserQueryRequest.getEmail());
        internalUser.setPassword(internalUserQueryRequest.getPassword());

        UserResponse<Void> response = internalUserService.updateUser(internalUser);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserResponse<Void>> delete(@RequestBody InternalUserQueryRequest internalUserQueryRequest) {
        String UserName = internalUserQueryRequest.getUserName();

        UserResponse<Void> response = internalUserService.deleteUser(UserName);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
