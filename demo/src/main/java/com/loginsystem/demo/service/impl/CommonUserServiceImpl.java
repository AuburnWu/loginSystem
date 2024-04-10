package com.loginsystem.demo.service.impl;

import com.loginsystem.demo.service.CommonUserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.regex.Pattern;

@Service
public class CommonUserServiceImpl implements CommonUserService {

    @Override
    public boolean pwdLengthCheck(int lengthLimit, String pwd) {
        return pwd.length() >= lengthLimit;
    }

    @Override
    public boolean pwdComplexCheck(String regex, String pwd) {
        return Pattern.compile(regex).matcher(pwd).matches();
    }

    @Override
    public boolean pwdTimeValidCheck(Timestamp limitDays, Timestamp setTime) {
//        Instant currentTime = Instant.now();
//        Instant pwdTime = setTime.toInstant();
//        Duration duration = Duration.between(pwdTime, currentTime);
//        long days = duration.toDays();
//        return days >= limitDays;
        return true;
    }

    @Override
    public boolean pwdRepeatCheck(String comparison, String pwd) {
        return comparison.equals(pwd);
    }
}
