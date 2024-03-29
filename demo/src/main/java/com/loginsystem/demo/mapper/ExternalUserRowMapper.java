package com.loginsystem.demo.mapper;

import com.loginsystem.demo.model.entity.ExternalUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExternalUserRowMapper implements RowMapper<ExternalUser> {
    @Override
    public ExternalUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExternalUser user = new ExternalUser();
        user.setUserId(rs.getInt("userId"));
        user.setEmail(rs.getString("email"));
        user.setNationalIdNo(rs.getString("National_ID_No"));
        user.setUnifiedBusinessNo(rs.getString("Unified_Business_No"));
        return user;
    }
}
