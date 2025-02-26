package com.loginsystem.demo.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class InternalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;

    private String email;

    private String password;

    private Integer createId;

    private Timestamp createDate;

    private Integer updateId;

    private Timestamp updateDate;
}
