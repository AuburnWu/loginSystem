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

import static java.sql.Types.NULL;

@Service
public class ExternalUserServiceImpl implements ExternalUserService {

    @Autowired
    ExternalUserDao externalUserDao;

    @Override
    public String createUser(ExternalUser externalUser) {
        String defaultPwd = genDefaultPwd(8 );
        externalUser.setPassword(defaultPwd);

        if(externalUserDao.insert(externalUser) > 0){
            return OperationResult.ACCOUNT_CREATE_SUCCESS;
        }
        return OperationResult.ACCOUNT_CREATE_FAILURE;
    }

    @Override
    public ExternalUserResponse<ExternalUser> loginUser(ExternalUser externalUser) {

        ExternalUserResponse<ExternalUser> externalUserResponse = new ExternalUserResponse<ExternalUser>();
        ExternalUser loginAccount = externalUserDao.login(externalUser);

        if(loginAccount != null){
            externalUserResponse.setReturnData(loginAccount);
            externalUserResponse.setOperateStatus(OperationResult.LOGIN_SUCCESS);
        }

        externalUserResponse.setOperateStatus(OperationResult.LOGIN_FAILURE);

        return externalUserResponse;

    }

    @Override
    public ExternalUserResponse<List<ExternalUser>> queryUsersByName(String keyWord) {
        ExternalUserResponse<List<ExternalUser>> externalUserResponse = new ExternalUserResponse<List<ExternalUser>>();
        List<ExternalUser> selectResult = externalUserDao.selectByName(keyWord);

        if(!selectResult.isEmpty()){
            externalUserResponse.setReturnData(selectResult);
        }
        externalUserResponse.setOperateStatus(OperationResult.ACCOUNT_FOUND);

        return externalUserResponse;
    }

    @Override
    public String updataUser(ExternalUser externalUser) {

        if(externalUserDao.update(externalUser) > 0){
            return
        }


        return null;
    }

    @Override
    public String deleteUser(String userName) {

        externalUserDao.delete(userName);

        return null;
    }

    public String genDefaultPwd(int length){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes).substring(0, length);
    }
}
