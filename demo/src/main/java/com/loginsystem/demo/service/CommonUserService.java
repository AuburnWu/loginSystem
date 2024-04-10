package com.loginsystem.demo.service;

import java.sql.Timestamp;

public interface CommonUserService {
    public boolean pwdLengthCheck(int lengthLimit, String pwd);
    public boolean pwdComplexCheck(String regex, String pwd);
    public boolean pwdTimeValidCheck(Timestamp limitDays, Timestamp setTime);
    public boolean pwdRepeatCheck(String comparison, String pwd);

}
