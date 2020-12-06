package com.tournament.managerment.controller;

import com.tournament.managerment.dto.RestError;
import com.tournament.managerment.dto.UserLoginRequestDTO;
import com.tournament.managerment.dto.UserLoginResponseDTO;
import com.tournament.managerment.exception.BaseException;
import com.tournament.managerment.exception.user.LoginErrorException;
import com.tournament.managerment.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }

    //用户登录
    @ApiOperation("用户登录")
    @PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserLoginResponseDTO> userLogin(@RequestBody @Valid UserLoginRequestDTO request) throws LoginErrorException {
        logger.info("userName:{} secretKey:{}",request.getUserName(), request.getSecretKey());
        UserLoginResponseDTO userLoginResponse=userService.userLogin(request);
        logger.info("user:{} login in", request.getUserName());
        return ResponseEntity.ok(userLoginResponse);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<RestError> handleTournamentNotFound(BaseException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new RestError(ex));
    }
}
