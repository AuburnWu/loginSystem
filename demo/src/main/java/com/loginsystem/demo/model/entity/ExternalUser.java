package com.loginsystem.demo.model.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExternalUser {
    private Integer userId;

    private String userName;

    private String email;

    private String password;

    private String nationalIdNo;

    private String unifiedBusinessNo;

    private Integer createId;

    private Timestamp createDate;

    private Integer updateId;

    private Timestamp updateDate;
}
