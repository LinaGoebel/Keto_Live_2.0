package com.ketolive.service;

import com.ketolive.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    Product addProduct(Product product);
}
