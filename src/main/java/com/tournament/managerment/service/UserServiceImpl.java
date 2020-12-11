package com.tournament.managerment.service;

import com.tournament.managerment.dto.UserLoginRequestDTO;
import com.tournament.managerment.dto.UserLoginResponseDTO;
import com.tournament.managerment.entity.UserDO;
import com.tournament.managerment.exception.user.LoginErrorException;
import com.tournament.managerment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }


    @Override
    public boolean isUserNameExist(String userName) {
        UserDO user=userRepository.getUserByUserName(userName);
        if(user == null) {
            logger.info("user:{} is nonexistent", userName);
            return false;
        }
        logger.info("user:{} exists", userName);
        return true;
    }

    @Override
    public UserLoginResponseDTO userLogin(UserLoginRequestDTO loginInfo) throws LoginErrorException {
        //先判断账号是否存在
        boolean isExist=isUserNameExist(loginInfo.getUserName());
        List<String> hostedTournament=new LinkedList<>();
        if(isExist) { //若存在则判断密码是否正确
            UserDO user=userRepository.getUserByNameAndPass(loginInfo.getUserName(), loginInfo.getSecretKey());
            if(user == null) { //若密码错误
                logger.info("user:{} login failure", loginInfo.getUserName());
                throw new LoginErrorException(loginInfo.getUserName());
            }
            else { //若密码正确则获取主办的tournaments
                logger.info("user:{} login success", loginInfo.getUserName());
                hostedTournament=getHostedTournament(loginInfo.getUserName());
            }
        }
        else { //若不存在则直接注册并登录
            UserDO.UserBuilder userBuilder=UserDO.builder();
            userBuilder.withUserName(loginInfo.getUserName())
                    .withSecretKey(loginInfo.getSecretKey())
                    .build();
            UserDO userRegister=userBuilder.build();
            userRepository.save(userRegister);
            logger.info("Register and login in user:{}",loginInfo.getUserName());
        }
        return UserLoginResponseDTO.builder()
                .withUserName(loginInfo.getUserName())
                .withHostedTournament(hostedTournament)
                .withMsg("success")
                .build();
    }

    @Override
    public List<String> getHostedTournament(String userName) {
        List<String> hostedTournament=userRepository.getHostedTournamentByUserName(userName);
        logger.debug("The user hosts {} tournaments", hostedTournament.size());
        return hostedTournament;
    }
}
