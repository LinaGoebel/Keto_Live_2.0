package com.ketolive.service;

import com.ketolive.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product addProduct(Product product);
}
