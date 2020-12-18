package com.tournament.managerment.exception.tournament;

import com.tournament.managerment.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidUserHeaderException extends BaseException {

    private static final long serialVersionUID = -1909654886770732320L;

    public InvalidUserHeaderException() {
        super(HttpStatus.UNAUTHORIZED, "missing User-Name in HTTP Header");
    }
}
