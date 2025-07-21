package com.jun.inventory_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockUpdateDto {
    @NotNull (message = "Input quantity")
    @Min (value = 0, message = "Input 0 or over")
    private Integer quantity;
}
