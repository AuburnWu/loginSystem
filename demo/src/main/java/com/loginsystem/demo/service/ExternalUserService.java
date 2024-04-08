package com.loginsystem.demo.service;

import com.loginsystem.demo.dto.UserResponse;
import com.loginsystem.demo.model.entity.ExternalUser;

import java.util.List;

public interface ExternalUserService {
    UserResponse<Void> createUser(ExternalUser externalUser);
    UserResponse<ExternalUser> loginUser(ExternalUser externalUser);
    UserResponse<List<ExternalUser>> queryUsersByName(String keyWord);
    UserResponse<Void> updateUser(ExternalUser externalUser);
    UserResponse<Void> deleteUser(String userName);
}
