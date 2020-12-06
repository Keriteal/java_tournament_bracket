package com.tournament.managerment.dto;

public class UserLoginRequestDTO {
    private String userName;
    private String secretKey;

    public UserLoginRequestDTO() {
        this.userName=this.secretKey="";
    }
    public UserLoginRequestDTO(String userName, String secretKey) {
        this.userName=userName;
        this.secretKey=secretKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
