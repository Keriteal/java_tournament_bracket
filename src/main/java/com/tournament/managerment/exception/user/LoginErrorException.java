package com.tournament.managerment.exception.user;

import com.tournament.managerment.exception.BaseException;
import org.springframework.http.HttpStatus;

public class LoginErrorException extends BaseException {
    private static final long serialVersionUID = -119827672287342330L;

    public LoginErrorException(String userName){
        super(HttpStatus.BAD_REQUEST, "login failure: " + userName);
    }
}
