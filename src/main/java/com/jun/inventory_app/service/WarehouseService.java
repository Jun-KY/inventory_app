package com.jun.inventory_app.service;

import com.jun.inventory_app.dto.WarehouseDto;
import com.jun.inventory_app.model.Warehouse;
import com.jun.inventory_app.qeury.WarehouseQueryRepository;
import com.jun.inventory_app.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseQueryRepository warehouseQueryRepository;

    public Warehouse create (WarehouseDto dto){
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        return warehouseRepository.save(warehouse);
    }
}
