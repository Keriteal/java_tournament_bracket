package com.tournament.managerment.dto;

import java.util.LinkedList;
import java.util.List;

public class UserLoginResponseDTO {
    private String userName;
    private List<String> hostedTournament = new LinkedList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getHostedTournament() {
        return hostedTournament;
    }

    public void setHostedTournament(List<String> hostedTournament) {
        this.hostedTournament = hostedTournament;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String userName;
        private List<String> hostedTournament = new LinkedList<>();

        private Builder() {
        }

        public static Builder anUserLoginResponseDTO() {
            return new Builder();
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withHostedTournament(List<String> hostedTournament) {
            this.hostedTournament = hostedTournament;
            return this;
        }

        public UserLoginResponseDTO build() {
            UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
            userLoginResponseDTO.setUserName(userName);
            userLoginResponseDTO.setHostedTournament(hostedTournament);
            return userLoginResponseDTO;
        }
    }
}
