package com.jun.inventory_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ProductDto {
    @NotBlank(message = "Input product name")
    private String name;

    @NotNull(message = "input price")
    @PositiveOrZero(message = "price must be same or bigger than 0")
    private BigDecimal price;
}
