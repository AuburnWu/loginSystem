package com.loginsystem.demo.controller;

import com.loginsystem.demo.dto.ExternalUserCreateRequest;
import com.loginsystem.demo.dto.ExternalUserLoginRequest;
import com.loginsystem.demo.dto.ExternalUserQueryRequest;
import com.loginsystem.demo.dto.ExternalUserResponse;
import com.loginsystem.demo.enums.OperationResult;
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
    public ResponseEntity register(@RequestBody ExternalUserCreateRequest externalUserCreateRequest) {
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(externalUserCreateRequest.getUserName());
        externalUser.setEmail(externalUserCreateRequest.getEmail());
        externalUser.setNationalIdNo(externalUserCreateRequest.getNationalIdNo());
        externalUser.setUnifiedBusinessNo(externalUserCreateRequest.getUnifiedBusinessNo());

        ExternalUserResponse<Void> response = externalUserService.createUser(externalUser);

        return ResponseEntity.status(HttpStatus.OK).body(response.getOperateStatus().getMessage());

    }

    @PostMapping("/login")
    public ResponseEntity<ExternalUserResponse<ExternalUser>> login(@RequestBody ExternalUserLoginRequest externalUserLoginRequest) {
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(externalUserLoginRequest.getUserName());
        externalUser.setPassword(externalUserLoginRequest.getPassword());

        ExternalUserResponse<ExternalUser> response = externalUserService.loginUser(externalUser);
        if (response.getOperateStatus() == OperationResult.LOGIN_SUCCESS) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
//            return externalUserService.loginUser(externalUser);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


    @PostMapping("/select")
    public ResponseEntity<ExternalUserResponse<List<ExternalUser>>> selectUserByName(@RequestBody ExternalUserQueryRequest externalUserQueryRequest) {
        ExternalUser externalUser = new ExternalUser();
        ExternalUserResponse<List<ExternalUser>> response = externalUserService.queryUsersByName(externalUserQueryRequest.getUserName());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ExternalUserResponse<Void>> update(@RequestBody ExternalUserQueryRequest externalUserQueryRequest) {
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserName(externalUserQueryRequest.getUserName());
        externalUser.setEmail(externalUserQueryRequest.getEmail());
        externalUser.setPassword(externalUserQueryRequest.getPassword());
        externalUser.setNationalIdNo(externalUserQueryRequest.getNationalIdNo());
        externalUser.setUnifiedBusinessNo(externalUserQueryRequest.getUnifiedBusinessNo());

        ExternalUserResponse<Void> response = externalUserService.updateUser(externalUser);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ExternalUserResponse<Void>> delete(@RequestBody ExternalUserQueryRequest externalUserQueryRequest) {
        String UserName = externalUserQueryRequest.getUserName();

        ExternalUserResponse<Void> response = externalUserService.deleteUser(UserName);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
