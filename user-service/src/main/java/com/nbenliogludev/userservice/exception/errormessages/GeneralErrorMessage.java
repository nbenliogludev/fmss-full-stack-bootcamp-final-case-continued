package com.nbenliogludev.userservice.exception.errormessages;

import java.time.LocalDateTime;

/**
 * @author nbenliogludev
 */
public record GeneralErrorMessage(LocalDateTime date, String message, String description) {

}