package com.jun.inventory_app.controller;

import com.jun.inventory_app.dto.WarehouseDto;
import com.jun.inventory_app.model.Warehouse;
import com.jun.inventory_app.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.ReadableByteChannel;

@RestController @RequiredArgsConstructor @RequestMapping("/api/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @PostMapping
    public Warehouse create(@Valid @RequestBody WarehouseDto dto){
        return warehouseService.create(dto);
    }
}
