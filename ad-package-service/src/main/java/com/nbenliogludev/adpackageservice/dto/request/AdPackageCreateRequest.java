package com.nbenliogludev.adpackageservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * @author nbenliogludev
 */
@Builder
public record AdPackageCreateRequest (

        @NotNull(message = "User Id is required.")
        Long userId
) {
}
