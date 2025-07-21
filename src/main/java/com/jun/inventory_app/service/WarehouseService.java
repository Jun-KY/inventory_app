package com.jun.inventory_app.service;

import com.jun.inventory_app.dto.WarehouseDto;
import com.jun.inventory_app.model.Stock;
import com.jun.inventory_app.model.Warehouse;
import com.jun.inventory_app.qeury.WarehouseQueryRepository;
import com.jun.inventory_app.qeury.WarehouseSearchCondition;
import com.jun.inventory_app.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service @RequiredArgsConstructor @Transactional
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseQueryRepository queryRepository;

    public List<Warehouse> search (WarehouseSearchCondition condition){
        return queryRepository.search(condition);
    }


    public Warehouse getById(Long id){
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("warehouse not found id = " + id));
    }

    public Warehouse create (WarehouseDto dto){
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        return warehouseRepository.save(warehouse);
    }

    public Warehouse update (Long id, WarehouseDto dto){
        Warehouse warehouse = getById(id);
        if(dto.getName() != null) warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());

        return warehouseRepository.save(warehouse);
    }

    public void delete (Long id){
        warehouseRepository.deleteById(id);
    }
}
