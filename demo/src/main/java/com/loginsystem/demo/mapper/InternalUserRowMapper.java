package com.loginsystem.demo.mapper;

import com.loginsystem.demo.model.entity.InternalUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InternalUserRowMapper implements RowMapper<InternalUser> {
    @Override
    public InternalUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        InternalUser internalUser = new InternalUser();
        internalUser.setUserId(rs.getInt("userId"));
        internalUser.setUserName(rs.getString("userName"));
        internalUser.setEmail(rs.getString("email"));
        return internalUser;
    }
}
