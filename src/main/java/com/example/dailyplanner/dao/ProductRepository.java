package com.example.dailyplanner.dao;

import com.example.dailyplanner.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findFirstByItemName(String itemName);
}
