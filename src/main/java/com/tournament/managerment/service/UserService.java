package com.tournament.managerment.service;

import com.tournament.managerment.dto.UserLoginRequestDTO;
import com.tournament.managerment.dto.UserLoginResponseDTO;
import com.tournament.managerment.exception.user.LoginErrorException;

import java.util.List;

public interface UserService {
    //判断输入账号是否存在
    boolean isUserNameExist(String userName);

    //存在账号情况下，判断密码是否正确；帐号不存在，直接注册并登录
    UserLoginResponseDTO userLogin(UserLoginRequestDTO loginInfo)
        throws LoginErrorException;

    //获取该user主办的tournaments
    List<String> getTournamentsHost(String userName);
}
