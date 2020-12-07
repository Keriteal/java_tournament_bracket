package com.tournament.managerment.exception.tournament;

import com.tournament.managerment.exception.BaseException;
import org.springframework.http.HttpStatus;

public class FormatNotSupportException extends BaseException {

    private static final long serialVersionUID = 7504301528139372367L;

    public FormatNotSupportException(String format) {
        super(HttpStatus.NOT_FOUND, format + "(format) is not supported");
    }
}
