package com.loginsystem.demo.service;

import com.loginsystem.demo.dto.UserResponse;
import com.loginsystem.demo.model.entity.InternalUser;

import java.util.List;

public interface InternalUserService {

    UserResponse<Void> createUser(InternalUser internalUser);
    UserResponse<InternalUser> loginUser(InternalUser internalUser);
    UserResponse<List<InternalUser>> queryUsersByName(String keyWord);
    UserResponse<Void> updateUser(InternalUser internalUser);
    UserResponse<Void> deleteUser(String userName);
}
