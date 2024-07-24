package com.nbenliogludev.adservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nbenliogludev
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdNotFoundException extends RuntimeException {

    public AdNotFoundException(String message) {
        super(message);
    }
}
