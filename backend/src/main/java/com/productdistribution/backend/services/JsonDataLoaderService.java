package com.productdistribution.backend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdistribution.backend.entities.Product;
import com.productdistribution.backend.entities.Store;
import com.productdistribution.backend.entities.Warehouse;
import com.productdistribution.backend.exceptions.DataLoadingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class JsonDataLoaderService implements DataLoaderService {

    @Value("${data.products.path}")
    private String productsFilePath;
    @Value("${data.stores.path}")
    private String storesFilePath;
    @Value("${data.warehouses.path}")
    private String warehousesFilePath;

    private final ObjectMapper objectMapper;

    public JsonDataLoaderService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Product> loadProducts() {
        try {
            return objectMapper.readValue(new File(productsFilePath), new TypeReference<List<Product>>() {});
        } catch (IOException ex) {
            throw new DataLoadingException("Error loading products from file: " + productsFilePath, ex);
        }
    }

    @Override
    public List<Store> loadStores() {
        try {
            return objectMapper.readValue(new File(storesFilePath), new TypeReference<List<Store>>() {});
        } catch (IOException ex) {
            throw new DataLoadingException("Error loading stores from file: " + storesFilePath, ex);
        }
    }

    @Override
    public List<Warehouse> loadWarehouses() {
        try {
            return objectMapper.readValue(new File(warehousesFilePath), new TypeReference<List<Warehouse>>() {});
        } catch (IOException ex) {
            throw new DataLoadingException("Error loading warehouses from file: " + warehousesFilePath, ex);
        }
    }
}