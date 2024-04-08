package com.loginsystem.demo.service.impl;

import com.loginsystem.demo.dto.UserResponse;
import com.loginsystem.demo.enums.OperationResult;
import com.loginsystem.demo.model.dao.InternalUserDao;
import com.loginsystem.demo.model.entity.InternalUser;
import com.loginsystem.demo.service.InternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class InternalUserServiceImpl implements InternalUserService {

    @Autowired
    InternalUserDao internalUserDao;

    @Override
    public UserResponse<Void> createUser(InternalUser internalUser) {

        UserResponse<Void> UserResponse = new UserResponse<Void>();

        if (internalUserDao.insert(internalUser) > 0) {
            UserResponse.setOperateStatus(OperationResult.ACCOUNT_CREATE_SUCCESS);
        } else {
            UserResponse.setOperateStatus(OperationResult.ACCOUNT_CREATE_FAILURE);
        }

        return UserResponse;
    }

    @Override
    public UserResponse<InternalUser> loginUser(InternalUser internalUser) {

        UserResponse<InternalUser> UserResponse = new UserResponse<InternalUser>();
        InternalUser loginAccount = internalUserDao.login(internalUser);

        if(loginAccount != null){
            UserResponse.setReturnData(loginAccount);
            UserResponse.setOperateStatus(OperationResult.LOGIN_SUCCESS);
        }

//        UserResponse.setReturnData(null);
        UserResponse.setOperateStatus(OperationResult.LOGIN_FAILURE);

        return UserResponse;

    }

    @Override
    public UserResponse<List<InternalUser>> queryUsersByName(String keyWord) {
        UserResponse<List<InternalUser>> UserResponse = new UserResponse<List<InternalUser>>();
        List<InternalUser> selectResult = internalUserDao.selectByName(keyWord);

        System.err.println("selectResult == " + selectResult);
        if(!selectResult.isEmpty()){
            UserResponse.setReturnData(selectResult);
        }
        UserResponse.setOperateStatus(OperationResult.ACCOUNT_FOUND);

        return UserResponse;
    }

    @Override
    public UserResponse<Void> updateUser(InternalUser internalUser) {
        UserResponse<Void> UserResponse = new UserResponse<Void>();
        internalUserDao.update(internalUser);

        UserResponse.setOperateStatus(OperationResult.ACCOUNT_UPDATE_SUCCESS);

        return UserResponse;
    }

    @Override
    public UserResponse<Void> deleteUser(String userName) {
        UserResponse<Void> UserResponse = new UserResponse<Void>();
        internalUserDao.delete(userName);

        UserResponse.setOperateStatus(OperationResult.ACCOUNT_DELETE_SUCCESS);

        return UserResponse;
    }

    public String genDefaultPwd(int length){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes).substring(0, length);
    }
}
