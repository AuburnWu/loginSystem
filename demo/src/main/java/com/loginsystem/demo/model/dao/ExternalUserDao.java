package com.loginsystem.demo.model.dao;

import com.loginsystem.demo.model.entity.ExternalUser;

import java.util.List;

public interface ExternalUserDao {

    int insert(ExternalUser externalUser);
    ExternalUser login(ExternalUser externalUser);
    List<ExternalUser> selectByName(String userName);
    int update(ExternalUser externalUser);
    int delete(String userName);
}
