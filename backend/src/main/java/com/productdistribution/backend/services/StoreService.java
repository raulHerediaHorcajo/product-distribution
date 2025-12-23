package com.productdistribution.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productdistribution.backend.entities.Store;
import com.productdistribution.backend.exceptions.ResourceNotFoundException;
import com.productdistribution.backend.repositories.StoreRepository;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final DataLoaderService dataLoaderService;

    @Autowired
    public StoreService(StoreRepository storeRepository, DataLoaderService dataLoaderService) {
        this.storeRepository = storeRepository;
        this.dataLoaderService = dataLoaderService;
    }

    public List<Store> loadStoresFromJson() {
        return dataLoaderService.loadStores();
    }

    public List<Store> refreshStoresFromJson() {
        deleteAll();
        List<Store> stores = loadStoresFromJson();
        addAll(stores);
        return stores;
    }

    public void add(Store store) {
        storeRepository.save(store);
    }

    public void addAll(List<Store> stores) {
        storeRepository.saveAll(stores);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(String id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store", id));
    }

    public void deleteAll() {
        storeRepository.deleteAll();
    }
}