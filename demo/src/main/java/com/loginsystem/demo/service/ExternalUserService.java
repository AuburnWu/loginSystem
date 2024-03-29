package com.loginsystem.demo.service;

import com.loginsystem.demo.model.entity.ExternalUser;

public interface ExternalUserService {
    String createUser(ExternalUser externalUser);
    String loginUser(ExternalUser externalUser);
    String queryUserByName(String userName);
    String updataUser(ExternalUser externalUser);
    String deleteUser(String userName);
}
