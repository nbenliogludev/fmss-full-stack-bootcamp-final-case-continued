package com.nbenliogludev.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nbenliogludev
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserEmailAlreadyExistException extends RuntimeException {

    public UserEmailAlreadyExistException(String message) {
        super(message);
    }
}
