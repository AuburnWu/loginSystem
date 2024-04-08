package com.loginsystem.demo.mapper;

import com.loginsystem.demo.model.entity.ExternalUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExternalUserRowMapper implements RowMapper<ExternalUser> {
    @Override
    public ExternalUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExternalUser externalUser = new ExternalUser();
        externalUser.setUserId(rs.getInt("userId"));
        externalUser.setUserName(rs.getString("userName"));
        externalUser.setEmail(rs.getString("email"));
        externalUser.setNationalIdNo(rs.getString("National_ID_No"));
        externalUser.setUnifiedBusinessNo(rs.getString("Unified_Business_No"));
        return externalUser;
    }
}
