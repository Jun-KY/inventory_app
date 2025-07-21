package com.jun.inventory_app.service;

import com.jun.inventory_app.dto.StockDto;
import com.jun.inventory_app.dto.StockUpdateDto;
import com.jun.inventory_app.model.Product;
import com.jun.inventory_app.model.Stock;
import com.jun.inventory_app.model.Warehouse;
import com.jun.inventory_app.qeury.StockQueryRepository;
import com.jun.inventory_app.qeury.StockSearchCondition;
import com.jun.inventory_app.repository.ProductRepository;
import com.jun.inventory_app.repository.StockRepository;
import com.jun.inventory_app.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.query.HbmResultSetMappingDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service @RequiredArgsConstructor @Transactional
public class StockService {
    private final StockRepository stockRepository;
    private final StockQueryRepository queryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public List<Stock> search (StockSearchCondition condition){
        return queryRepository.search(condition);
    }

    public Stock getById(Long id){
        return stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No stock found"));
    }

    public Stock create (StockDto dto){
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new NoSuchElementException("No product found"));

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new NoSuchElementException("No warehouse found"));

        Stock stock = new Stock();
        stock.setProduct(product);
        stock.setWarehouse(warehouse);

        stock.setQuantity (dto.getQuantity());
        return stockRepository.save(stock);
    }

    public Stock update(Long id, StockUpdateDto dto){
        Stock stock = getById(id);
        stock.setQuantity(dto.getQuantity());
        return stockRepository.save(stock);
    }

    public void delete(Long id){
        stockRepository.deleteById(id);
    }

    public void transferStock(Long productId, Long srcWarehouseId, Long destWarehouseId, int quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("No product found"));
        Warehouse srcWarehouse = warehouseRepository.findById(srcWarehouseId)
                .orElseThrow(() -> new NoSuchElementException("Cannot find departure warehouse"));
        Warehouse destWarehouse = warehouseRepository.findById(destWarehouseId)
                .orElseThrow(() -> new NoSuchElementException("Cannot find arrival warehouse"));

        Stock srcStock = stockRepository.findByProductIdAndWarehouseId(productId, srcWarehouseId)
                .orElseThrow(() -> new IllegalStateException("No stock exist"));
        if (srcStock.getQuantity() < quantity){
            throw new IllegalStateException("Not enough stock");
        }
        srcStock.setQuantity(srcStock.getQuantity() - quantity);

        Stock destStock = stockRepository.findByProductIdAndWarehouseId(productId, destWarehouseId)
                .orElse(new Stock());
        destStock.setProduct(product);
        destStock.setWarehouse(destWarehouse);
        destStock.setQuantity(destStock.getQuantity() == null ? quantity : destStock.getQuantity() + quantity);

        stockRepository.saveAll(List.of(srcStock, destStock));
    }

}

