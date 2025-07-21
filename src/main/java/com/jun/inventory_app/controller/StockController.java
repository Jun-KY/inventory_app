package com.jun.inventory_app.controller;

import com.jun.inventory_app.dto.StockDto;
import com.jun.inventory_app.dto.StockUpdateDto;
import com.jun.inventory_app.model.Stock;
import com.jun.inventory_app.qeury.StockSearchCondition;
import com.jun.inventory_app.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor @RestController @RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    @GetMapping
    public List<Stock> search (StockSearchCondition condition){
        return stockService.search(condition);
    }

    @GetMapping("/{Id}")
    public Stock getById(@PathVariable Long id){
        return stockService.getById(id);
    }

    @PostMapping
    public Stock create (@Valid @RequestBody StockDto dto){
        return stockService.create(dto);
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable Long id, @Valid @RequestBody StockUpdateDto dto){
        return stockService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        stockService.getById(id);
    }

    @PostMapping("/transfer")
    public void transfer(
            @RequestParam Long productId,
            @RequestParam Long srcWarehouseId,
            @RequestParam Long destWarehouseId,
            @RequestParam int quantity
    ){
        stockService.transferStock(productId, srcWarehouseId, destWarehouseId, quantity);
    }
}
