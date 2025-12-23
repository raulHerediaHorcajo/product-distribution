package com.productdistribution.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productdistribution.backend.entities.Warehouse;
import com.productdistribution.backend.exceptions.ResourceNotFoundException;
import com.productdistribution.backend.repositories.WarehouseRepository;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final DataLoaderService dataLoaderService;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository, DataLoaderService dataLoaderService) {
        this.warehouseRepository = warehouseRepository;
        this.dataLoaderService = dataLoaderService;
    }

    public List<Warehouse> loadWarehousesFromJson() {
        return dataLoaderService.loadWarehouses();
    }

    public List<Warehouse> refreshWarehousesFromJson() {
        deleteAll();
        List<Warehouse> warehouses = loadWarehousesFromJson();
        addAll(warehouses);
        return warehouses;
    }

    public void add(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    public void addAll(List<Warehouse> warehouses) {
        warehouseRepository.saveAll(warehouses);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(String id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", id));
    }

    public void deleteAll() {
        warehouseRepository.deleteAll();
    }
}