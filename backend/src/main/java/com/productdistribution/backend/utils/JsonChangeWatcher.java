package com.productdistribution.backend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class JsonChangeWatcher {

    @Value("${data.products.path}")
    private String productsPath;
    @Value("${data.stores.path}")
    private String storesPath;
    @Value("${data.warehouses.path}")
    private String warehousesPath;

    private final Map<String, Long> lastModifiedMap = new HashMap<>();

    public boolean hasAnyChanged() {
        return hasChanged("products", productsPath)
            || hasChanged("stores", storesPath)
            || hasChanged("warehouses", warehousesPath);
    }

    private boolean hasChanged(String key, String filePath) {
        long lastModified = getLastModified(filePath);
        Long previousModified = lastModifiedMap.get(key);

        if (previousModified == null || previousModified != lastModified) {
            lastModifiedMap.put(key, lastModified);
            return true;
        }
        return false;
    }

    public void updateLastModifiedDates() {
        updateLastModified("products", productsPath);
        updateLastModified("stores", storesPath);
        updateLastModified("warehouses", warehousesPath);
    }

    private void updateLastModified(String key, String filePath) {
        long lastModified = getLastModified(filePath);
        lastModifiedMap.put(key, lastModified);
    }

    private long getLastModified(String filePath) {
        File file = new File(filePath);
        return file.lastModified();
    }
}