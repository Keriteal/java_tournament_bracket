package com.tournament.managerment.dto;

import java.util.LinkedList;
import java.util.List;

public class UserLoginResponseDTO {
    private String userName;
    private List<String> tournamentsHost = new LinkedList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getTournamentsHost() {
        return tournamentsHost;
    }

    public void setTournamentsHost(List<String> tournamentsHost) {
        this.tournamentsHost = tournamentsHost;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String userName;
        private List<String> tournamentsHost = new LinkedList<>();

        private Builder() {
        }

        public static Builder anUserLoginResponseDTO() {
            return new Builder();
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withTournamentsHost(List<String> tournamentsHost) {
            this.tournamentsHost = tournamentsHost;
            return this;
        }

        public UserLoginResponseDTO build() {
            UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
            userLoginResponseDTO.setUserName(userName);
            userLoginResponseDTO.setTournamentsHost(tournamentsHost);
            return userLoginResponseDTO;
        }
    }
}
