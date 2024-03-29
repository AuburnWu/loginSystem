package com.loginsystem.demo.model.dao;

import com.loginsystem.demo.model.entity.ExternalUser;

public interface ExternalUserDao {

    int insert(ExternalUser externalUser);
    int login(ExternalUser externalUser);
    int selectByName(String userName);
    int update(ExternalUser externalUser);
    int delete(String userName);
}
