package com.example.dailyplanner.service;
import com.example.dailyplanner.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProduct(int id);

    public void saveProduct(Product product);

    public void deleteProduct(int id);

    public Product getProductByName(String itemName);
}
