package com.loginsystem.demo.model.dao;

import com.loginsystem.demo.model.entity.InternalUser;

import java.util.List;

public interface InternalUserDao {

    int insert(InternalUser internalUser);
    InternalUser login(InternalUser internalUser);
    List<InternalUser> selectByName(String userName);
    int update(InternalUser internalUser);
    int delete(String userName);
}
