package com.loginsystem.demo.service.impl;

import com.loginsystem.demo.model.dao.ExternalUserDao;
import com.loginsystem.demo.model.entity.ExternalUser;
import com.loginsystem.demo.service.ExternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class ExternalUserServiceImpl implements ExternalUserService {

    @Autowired
    ExternalUserDao externalUserDao;

    @Override
    public String createUser(ExternalUser externalUser) {
        String defaultPwd = genDefaultPwd(8 );
        externalUser.setPassword(defaultPwd);
        if(externalUserDao.insert(externalUser) > 0){
            return defaultPwd;
        }
        return "";
    }

    @Override
    public String loginUser(ExternalUser externalUser) {

        externalUserDao.login(externalUser);

        return null;
    }

    @Override
    public String queryUserByName(String userName) {

        externalUserDao.selectByName(userName);

        return null;
    }
    @Override
    public String updataUser(ExternalUser externalUser) {

        externalUserDao.update(externalUser);

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
