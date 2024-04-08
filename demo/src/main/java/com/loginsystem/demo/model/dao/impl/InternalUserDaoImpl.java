package com.loginsystem.demo.model.dao.impl;

import com.loginsystem.demo.mapper.InternalUserRowMapper;
import com.loginsystem.demo.model.dao.InternalUserDao;
import com.loginsystem.demo.model.entity.InternalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Repository
public class InternalUserDaoImpl implements InternalUserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int insert(InternalUser internalUser) {

        String sql = "SELECT MAX(userId) FROM  internalUser";
        Integer latestId = namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
        latestId = latestId == null ? 0 : latestId;

        sql = "INSERT INTO internalUser (userName, email, password, createId, createDate, updateId, updateDate) " +
                "VALUES (:userName, :email, :password, :createId, :createDate, :updateId, :updateDate)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", internalUser.getUserName());
        params.addValue("email", internalUser.getEmail());
        params.addValue("password", internalUser.getPassword());
        params.addValue("createId", latestId + 1);
        params.addValue("createDate", new Timestamp(System.currentTimeMillis()));
        params.addValue("updateId", latestId + 1);
        params.addValue("updateDate", new Timestamp(System.currentTimeMillis()));

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public InternalUser login(InternalUser internalUser) {

        String sql = "SELECT userId, userName, email "
                + "FROM internalUser "
                + "WHERE "
                + "userName = :userName "
                + "AND "
                + "password = :password ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", internalUser.getUserName());
        params.addValue("password", internalUser.getPassword());

        List<InternalUser> accountData = namedParameterJdbcTemplate.query(sql, params, new InternalUserRowMapper());
        return accountData.isEmpty() ? null : accountData.get(0);

    }

    @Override
    public List<InternalUser> selectByName(String userName) {

        String sql = "SELECT userId, userName, email "
                + "FROM internalUser "
                + "WHERE "
                + "userName like :userName";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", "%" + userName + "%");

        return namedParameterJdbcTemplate.query(sql, params, new InternalUserRowMapper());
    }

    @Override
    public int update(InternalUser internalUser) {

        String sql =
                "UPDATE internalUser "
                + "SET "
                + "email = :email, "
                + "password = :password, "
                + "createId = :createId, "
                + "createDate = :createDate, "
                + "updateId = :updateId, "
                + "updateDate = :updateDate "
                + "WHERE userName = :userName";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", internalUser.getUserName());
        params.addValue("email", internalUser.getEmail());
        params.addValue("password", internalUser.getPassword());
        params.addValue("createId", internalUser.getCreateId());
        params.addValue("createDate", internalUser.getCreateDate());
        params.addValue("updateId", internalUser.getUpdateId());
        params.addValue("updateDate", internalUser.getUpdateDate());

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(String userName) {

        String sql = "DELETE FROM internalUser WHERE userName = :userName";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", userName);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
