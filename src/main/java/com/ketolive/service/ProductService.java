package com.ketolive.service;

import com.ketolive.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Page<Product> getAllProducts(Pageable pageable);

    Product getProductById(String id);

    Product addProduct(Product product);

    Product updateProduct(String id, Product product);

    void deleteProduct(String id);

    List<Product> searchProducts(String name);

    Page<Product> searchProducts(String name, Pageable pageable);

    List<Product> getByCategory(String category);

    Page<Product> getByCategory(String category, Pageable pageable);

    List<Product> getByMaxNetCarbs(Double maxNetCarbs);

    Page<Product> getByMaxNetCarbs(Double maxNetCarbs, Pageable pageable);
}
