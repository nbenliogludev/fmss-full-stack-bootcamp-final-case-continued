package com.nbenliogludev.adservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nbenliogludev
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAdPackageException extends RuntimeException {
    public InvalidAdPackageException(String message) {
        super(message);
    }
}
