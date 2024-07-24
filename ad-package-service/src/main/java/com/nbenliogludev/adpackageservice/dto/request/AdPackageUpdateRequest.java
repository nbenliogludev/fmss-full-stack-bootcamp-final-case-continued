package com.nbenliogludev.adpackageservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * @author nbenliogludev
 */
@Builder
public record AdPackageUpdateRequest (
        @NotNull(message = "Ad package number of ads is required.")
        int numberOfAds
) {
}
