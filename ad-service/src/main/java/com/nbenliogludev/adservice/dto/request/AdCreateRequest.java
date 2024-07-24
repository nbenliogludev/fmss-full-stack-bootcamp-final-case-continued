package com.nbenliogludev.adservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * @author nbenliogludev
 */
@Builder
public record AdCreateRequest (
    @NotNull(message = "User Id is required.")
    Long userId,

    @NotBlank(message = "Title is required.")
    @Length(max = 80, message = "Title can not be longer than 120 characters.")
    String title,

    @NotBlank(message = "Surname is required.")
    @Length(max = 80, message = "Description can not be longer than 500 characters.")
    String description,

    @NotNull(message = "Amount is required.")
    BigDecimal amount
) {
}