package com.nbenliogludev.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nbenliogludev
 */
@Builder
public record UserResponse (
    Long id,
    String name,
    String surname,
    String email
) {
}
