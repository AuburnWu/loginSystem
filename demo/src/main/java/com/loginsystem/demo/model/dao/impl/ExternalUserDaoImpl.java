package com.loginsystem.demo.model.dao.impl;

import com.loginsystem.demo.model.dao.ExternalUserDao;
import com.loginsystem.demo.model.entity.ExternalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;

@Repository
public class ExternalUserDaoImpl implements ExternalUserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int insert(ExternalUser externalUser) {

        String sql = "SELECT MAX(userId) FROM  externalUser";
        Integer latestId = namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
        System.out.println("latestId " + latestId);

        sql = "INSERT INTO externalUser (userName, email, password, National_ID_No, Unified_Business_No, createId, createDate, updateId, updateDate) " +
                "VALUES (:userName, :email, :password, :nationalIdNo, :unifiedBusinessNo, :createId, :createDate, :updateId, :updateDate)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", externalUser.getUserName());
        params.addValue("email", externalUser.getEmail());
        params.addValue("password", externalUser.getPassword());
        params.addValue("nationalIdNo", externalUser.getNationalIdNo());
        params.addValue("unifiedBusinessNo", externalUser.getUnifiedBusinessNo());
        params.addValue("createId", latestId + 1);
        params.addValue("createDate",  new Timestamp(System.currentTimeMillis()));
        params.addValue("updateId", latestId + 1);
        params.addValue("updateDate",  new Timestamp(System.currentTimeMillis()));

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int login(ExternalUser externalUser) {

        return 0;
    }

    @Override
    public int selectByName(String userName) {
        return 0;
    }

    @Override
    public int update(ExternalUser externalUser) {
        return 0;
    }

    @Override
    public int delete(String userName) {
        return 0;
    }
}
