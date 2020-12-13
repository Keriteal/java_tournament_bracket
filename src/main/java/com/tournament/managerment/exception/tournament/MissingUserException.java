package com.tournament.managerment.exception.tournament;

import com.tournament.managerment.exception.BaseException;
import org.springframework.http.HttpStatus;

public class MissingUserException extends BaseException {

    private static final long serialVersionUID = -1909654886770732320L;

    public  MissingUserException() {
        super(HttpStatus.BAD_REQUEST, "missing User-Name in HTTP Header");
    }
}
