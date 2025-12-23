package com.productdistribution.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productdistribution.backend.entities.ProductItem;

import java.util.UUID;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, UUID> {
}