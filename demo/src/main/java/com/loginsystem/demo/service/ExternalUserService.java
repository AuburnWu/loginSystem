package com.loginsystem.demo.service;

import com.loginsystem.demo.dto.ExternalUserResponse;
import com.loginsystem.demo.model.entity.ExternalUser;

import java.util.List;

public interface ExternalUserService {
    String createUser(ExternalUser externalUser);
    ExternalUserResponse<ExternalUser> loginUser(ExternalUser externalUser);
    ExternalUserResponse<List<ExternalUser>> queryUsersByName(String keyWord);
    String updataUser(ExternalUser externalUser);
    String deleteUser(String userName);
}
