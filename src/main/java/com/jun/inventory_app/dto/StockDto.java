package com.jun.inventory_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockDto {
    @NotNull(message = "Select product ID")
    private Long productId;

    @NotNull(message = "Select warehouse ID")
    private Long warehouseId;

    @NotNull(message = "Input quantity")
    @Min(value = 0, message = "Input 0 or over")
    private Integer quantity;
}
