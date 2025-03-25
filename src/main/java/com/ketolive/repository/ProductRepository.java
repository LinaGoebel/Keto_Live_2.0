package com.ketolive.repository;

import com.ketolive.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Поиск продуктов по названию
    List<Product> findByNameContainingIgnoreCase(String name);

    //Возвращает список продуктов по названию частями (по страницам)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Поиск продуктов по категории
    List<Product> findByCategory(String category);

    //Возвращает список продуктов по категории (по страницам)
    Page<Product> findByCategory(String category, Pageable pageable);

    // Находит продукты с содержанием чистых углеводов меньше или равно указанному значению.
    List<Product> findByNetCarbsLessThanEqual(Double maxNetCarbs);

    //Находит продукты с содержанием чистых углеводов меньше или равно указанному значению с пагинацией.
    Page<Product> findByNetCarbsLessThanEqual(Double maxNetCarbs, Pageable pageable);
}
