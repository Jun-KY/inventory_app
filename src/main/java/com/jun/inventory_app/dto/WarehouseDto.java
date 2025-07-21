package com.jun.inventory_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
public class WarehouseDto {
    @NotBlank(message = "Input Warehouse name")
    private String name;

    @Size(max = 200, message = "Location information is limited to 200 characters.")
    private String location;
}
