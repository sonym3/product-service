package com.sony.microservice.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(@NotBlank(message = "Name cant be blank")
                             @Size(max = 20, message = "Name must be less than 20 chars")
                             String name,
                             @NotBlank(message = "Description cant be blank")
                             @Size(max = 50, message = "Description must be less than 50 chars")
                             String description,
                             @NotNull
                             @Digits(integer = 5, fraction = 2, message = "maximum 5 digits and 2 decimals")
                             @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0.0")
                             BigDecimal price) {
}
