package com.productdistribution.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productdistribution.backend.entities.Product;
import com.productdistribution.backend.exceptions.ResourceNotFoundException;
import com.productdistribution.backend.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DataLoaderService dataLoaderService;

    @Autowired
    public ProductService(ProductRepository productRepository, DataLoaderService dataLoaderService) {
        this.productRepository = productRepository;
        this.dataLoaderService = dataLoaderService;
    }

    public List<Product> loadProductsFromJson() {
        return dataLoaderService.loadProducts();
    }

    @Transactional
    public List<Product> refreshProductsFromJson() {
        deleteAll();
        List<Product> products = loadProductsFromJson();
        addAll(products);
        return products;
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void addAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }
}