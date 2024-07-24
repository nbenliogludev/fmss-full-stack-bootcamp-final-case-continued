package com.nbenliogludev.adpackageservice.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author nbenliogludev
 */
@Builder
public record InfoLogDTO(
        String service,
        LocalDateTime timestamp,
        String message,
        String description
) {
}
