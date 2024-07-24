package com.nbenliogludev.adservice.dto.response;

import com.nbenliogludev.adservice.enums.AdStatus;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * @author nbenliogludev
 */
@Builder
public record AdResponse (
    Long id,
    String title,
    String description,
    BigDecimal amount,
    AdStatus status
) {
}
