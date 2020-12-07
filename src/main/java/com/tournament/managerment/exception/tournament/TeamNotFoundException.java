package com.tournament.managerment.exception.tournament;

import com.tournament.managerment.exception.BaseException;
import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends BaseException {

    private static final long serialVersionUID = -5359307750045742744L;

    public TeamNotFoundException(String teamName) {
        super(HttpStatus.NOT_FOUND, "Team( " + teamName + " ) not found");
    }
}
