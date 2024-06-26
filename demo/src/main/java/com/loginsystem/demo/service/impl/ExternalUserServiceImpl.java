package com.loginsystem.demo.service.impl;

import com.loginsystem.demo.dto.UserResponse;
import com.loginsystem.demo.enums.OperationResult;
import com.loginsystem.demo.model.dao.ExternalUserDao;
import com.loginsystem.demo.model.entity.ExternalUser;
import com.loginsystem.demo.service.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class ExternalUserServiceImpl implements ExternalUserService {

    @Autowired
    ExternalUserDao externalUserDao;

    @Override
    public UserResponse<Void> createUser(ExternalUser externalUser) {
//        設定預設密碼
        String defaultPwd = genDefaultPwd(8 );
        externalUser.setPassword(defaultPwd);

        UserResponse<Void> UserResponse = new UserResponse<Void>();

        if (externalUserDao.insert(externalUser) > 0) {
            UserResponse.setOperateStatus(OperationResult.ACCOUNT_CREATE_SUCCESS);
        } else {
            UserResponse.setOperateStatus(OperationResult.ACCOUNT_CREATE_FAILURE);
        }

        return UserResponse;
    }

    @Override
    public UserResponse<ExternalUser> loginUser(ExternalUser externalUser) {

        UserResponse<ExternalUser> UserResponse = new UserResponse<ExternalUser>();
        ExternalUser loginAccount = externalUserDao.login(externalUser);

        if(loginAccount != null){
            UserResponse.setReturnData(loginAccount);
            UserResponse.setOperateStatus(OperationResult.LOGIN_SUCCESS);
        }

//        UserResponse.setReturnData(null);
        UserResponse.setOperateStatus(OperationResult.LOGIN_FAILURE);

        return UserResponse;

    }

    @Override
    public UserResponse<List<ExternalUser>> queryUsersByName(String keyWord) {
        UserResponse<List<ExternalUser>> UserResponse = new UserResponse<List<ExternalUser>>();
        List<ExternalUser> selectResult = externalUserDao.selectByName(keyWord);

        System.err.println("selectResult == " + selectResult);
        if(!selectResult.isEmpty()){
            UserResponse.setReturnData(selectResult);
        }
        UserResponse.setOperateStatus(OperationResult.ACCOUNT_FOUND);

        return UserResponse;
    }

    @Override
    public UserResponse<Void> updateUser(ExternalUser externalUser) {
        UserResponse<Void> UserResponse = new UserResponse<Void>();
        externalUserDao.update(externalUser);

        UserResponse.setOperateStatus(OperationResult.ACCOUNT_UPDATE_SUCCESS);

        return UserResponse;
    }

    @Override
    public UserResponse<Void> deleteUser(String userName) {
        UserResponse<Void> UserResponse = new UserResponse<Void>();
        externalUserDao.delete(userName);

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
