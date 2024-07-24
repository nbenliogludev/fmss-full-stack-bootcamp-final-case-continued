package com.nbenliogludev.userservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author nbenliogludev
 */
@Builder
public record ErrorLogDTO (
        String service,
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String stackTrace
) {
}
