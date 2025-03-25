package com.ketolive.service;


import com.ketolive.exeption.ResourceNotFoundException;
import com.ketolive.model.Product;
import com.ketolive.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с ID " + id + " не найден"));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setNetCarbs(product.getNetCarbs());
        existingProduct.setFat(product.getFat());
        existingProduct.setProtein(product.getProtein());
        existingProduct.setFiber(product.getFiber());
        existingProduct.setCalories(product.getCalories());
        existingProduct.setGroup(product.getGroup());
        existingProduct.setDescription(product.getDescription());

        return productRepository.save(existingProduct);

    }

    @Override
    public void deleteProduct(String id) {
        Product product = getProductById(id);
        productRepository.delete(product);

    }

    @Override
    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Page<Product> searchProducts(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Page<Product> getByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }


    @Override
    public List<Product> getByMaxNetCarbs(Double maxNetCarbs) {
        return productRepository.findByNetCarbsLessThanEqual(maxNetCarbs);
    }

    @Override
    public Page<Product> getByMaxNetCarbs(Double maxNetCarbs, Pageable pageable) {
        return productRepository.findByNetCarbsLessThanEqual(maxNetCarbs, pageable);
    }
}
