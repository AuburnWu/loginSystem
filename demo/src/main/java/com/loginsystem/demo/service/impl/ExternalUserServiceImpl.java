package com.loginsystem.demo.service.impl;

import com.loginsystem.demo.dto.ExternalUserResponse;
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
    public ExternalUserResponse<Void> createUser(ExternalUser externalUser) {
//        設定預設密碼
        String defaultPwd = genDefaultPwd(8 );
        externalUser.setPassword(defaultPwd);

        ExternalUserResponse<Void> externalUserResponse = new ExternalUserResponse<Void>();

        if (externalUserDao.insert(externalUser) > 0) {
            externalUserResponse.setOperateStatus(OperationResult.ACCOUNT_CREATE_SUCCESS);
        } else {
            externalUserResponse.setOperateStatus(OperationResult.ACCOUNT_CREATE_FAILURE);
        }

        return externalUserResponse;
    }

    @Override
    public ExternalUserResponse<ExternalUser> loginUser(ExternalUser externalUser) {

        ExternalUserResponse<ExternalUser> externalUserResponse = new ExternalUserResponse<ExternalUser>();
        ExternalUser loginAccount = externalUserDao.login(externalUser);

        if(loginAccount != null){
            externalUserResponse.setReturnData(loginAccount);
            externalUserResponse.setOperateStatus(OperationResult.LOGIN_SUCCESS);
        }

//        externalUserResponse.setReturnData(null);
        externalUserResponse.setOperateStatus(OperationResult.LOGIN_FAILURE);

        return externalUserResponse;

    }

    @Override
    public ExternalUserResponse<List<ExternalUser>> queryUsersByName(String keyWord) {
        ExternalUserResponse<List<ExternalUser>> externalUserResponse = new ExternalUserResponse<List<ExternalUser>>();
        List<ExternalUser> selectResult = externalUserDao.selectByName(keyWord);

        System.err.println("selectResult == " + selectResult);
        if(!selectResult.isEmpty()){
            externalUserResponse.setReturnData(selectResult);
        }
        externalUserResponse.setOperateStatus(OperationResult.ACCOUNT_FOUND);

        return externalUserResponse;
    }

    @Override
    public ExternalUserResponse<Void> updateUser(ExternalUser externalUser) {
        ExternalUserResponse<Void> externalUserResponse = new ExternalUserResponse<Void>();
        externalUserDao.update(externalUser);

        externalUserResponse.setOperateStatus(OperationResult.ACCOUNT_UPDATE_SUCCESS);

        return externalUserResponse;
    }

    @Override
    public ExternalUserResponse<Void> deleteUser(String userName) {
        ExternalUserResponse<Void> externalUserResponse = new ExternalUserResponse<Void>();
        externalUserDao.delete(userName);

        externalUserResponse.setOperateStatus(OperationResult.ACCOUNT_DELETE_SUCCESS);

        return externalUserResponse;
    }

    public String genDefaultPwd(int length){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes).substring(0, length);
    }
}
