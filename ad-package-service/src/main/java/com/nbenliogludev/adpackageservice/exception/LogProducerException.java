package com.nbenliogludev.adpackageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author: nbenliogludev
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LogProducerException extends RuntimeException {

    public LogProducerException(String message) {
        super(message);
    }

    public LogProducerException(String message, Throwable cause) {
        super(message, cause);
    }
}
