package com.nbenliogludev.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nbenliogludev
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserReviewNotFoundException extends RuntimeException {

    public UserReviewNotFoundException(String message) {
        super(message);
    }
}
