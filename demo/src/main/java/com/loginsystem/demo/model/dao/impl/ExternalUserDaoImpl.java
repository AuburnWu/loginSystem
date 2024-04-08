package com.loginsystem.demo.model.dao.impl;

import com.loginsystem.demo.mapper.ExternalUserRowMapper;
import com.loginsystem.demo.model.dao.ExternalUserDao;
import com.loginsystem.demo.model.entity.ExternalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Repository
public class ExternalUserDaoImpl implements ExternalUserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int insert(ExternalUser externalUser) {

        String sql = "SELECT MAX(userId) FROM  externalUser";
        Integer latestId = namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
        latestId = latestId == null ? 0 : latestId;

        sql = "INSERT INTO externalUser (userName, email, password, National_ID_No, Unified_Business_No, createId, createDate, updateId, updateDate) " +
                "VALUES (:userName, :email, :password, :nationalIdNo, :unifiedBusinessNo, :createId, :createDate, :updateId, :updateDate)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", externalUser.getUserName());
        params.addValue("email", externalUser.getEmail());
        params.addValue("password", externalUser.getPassword());
        params.addValue("nationalIdNo", externalUser.getNationalIdNo());
        params.addValue("unifiedBusinessNo", externalUser.getUnifiedBusinessNo());
        params.addValue("createId", latestId + 1);
        params.addValue("createDate", new Timestamp(System.currentTimeMillis()));
        params.addValue("updateId", latestId + 1);
        params.addValue("updateDate", new Timestamp(System.currentTimeMillis()));

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public ExternalUser login(ExternalUser externalUser) {

        String sql = "SELECT userId, email, National_ID_No, Unified_Business_No "
                + "FROM externalUser "
                + "WHERE "
                + "userName = :userName "
                + "AND "
                + "password = :password ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", externalUser.getUserName());
        params.addValue("password", externalUser.getPassword());

        List<ExternalUser> accountData = namedParameterJdbcTemplate.query(sql, params, new ExternalUserRowMapper());
        return accountData.isEmpty() ? null : accountData.get(0);

    }

    @Override
    public List<ExternalUser> selectByName(String userName) {

        String sql = "SELECT userId, email, National_ID_No, Unified_Business_No "
                + "FROM externalUser "
                + "WHERE "
                + "userName like :userName";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", "%" + userName + "%");

        return namedParameterJdbcTemplate.query(sql, params, new ExternalUserRowMapper());
    }

    @Override
    public int update(ExternalUser externalUser) {

        String sql =
                "UPDATE externalUser "
                + "SET "
                + "email = :email, "
                + "password = :password, "
                + "National_ID_No = :nationalIdNo, "
                + "Unified_Business_No = :unifiedBusinessNo, "
                + "createId = :createId, "
                + "createDate = :createDate, "
                + "updateId = :updateId, "
                + "updateDate = :updateDate "
                + "WHERE userName = :userName";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", externalUser.getUserName());
        params.addValue("email", externalUser.getEmail());
        params.addValue("password", externalUser.getPassword());
        params.addValue("nationalIdNo", externalUser.getNationalIdNo());
        params.addValue("unifiedBusinessNo", externalUser.getUnifiedBusinessNo());
        params.addValue("createId", externalUser.getCreateId());
        params.addValue("createDate", externalUser.getCreateDate());
        params.addValue("updateId", externalUser.getUpdateId());
        params.addValue("updateDate", externalUser.getUpdateDate());

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(String userName) {

        String sql = "DELETE FROM externalUser WHERE userName = :userName";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", userName);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
