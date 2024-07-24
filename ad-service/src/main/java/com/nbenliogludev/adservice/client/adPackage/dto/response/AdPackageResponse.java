package com.nbenliogludev.adservice.client.adPackage.dto.response;

import com.nbenliogludev.adservice.client.adPackage.enums.AdPackageStatus;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author nbenliogludev
 */
@Builder
public record AdPackageResponse(
        Long id,
        Long userId,
        int numberOfAds,
        int validityPeriod,
        LocalDateTime expirationDate,
        AdPackageStatus status
) {
}
