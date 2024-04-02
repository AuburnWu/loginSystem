package com.loginsystem.demo.service;

import com.loginsystem.demo.dto.ExternalUserResponse;
import com.loginsystem.demo.model.entity.ExternalUser;

import java.util.List;

public interface ExternalUserService {
    ExternalUserResponse<Void> createUser(ExternalUser externalUser);
    ExternalUserResponse<ExternalUser> loginUser(ExternalUser externalUser);
    ExternalUserResponse<List<ExternalUser>> queryUsersByName(String keyWord);
    ExternalUserResponse<Void> updateUser(ExternalUser externalUser);
    ExternalUserResponse<Void> deleteUser(String userName);
}
