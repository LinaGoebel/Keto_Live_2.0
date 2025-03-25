package com.ketolive.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String category;//какие продукты можно съесть, какие нельзя и какие редко

    // Содержание чистых углеводов на 100г продукта.
    private Double netCarbs;

    // Содержание жиров на 100г продукта.
    private Double fat;

    // Содержание белков на 100г продукта.
    private Double protein;

    // Содержание клетчатки на 100г продукта.
    private Double fiber;

    // Калорийность на 100г продукта.
    private Double calories;

    // Группа продукта (мясо, молочные продукты, овощи и т.д.).
    private String group;

    // Описание продукта и рекомендации по его использованию на кето-диете.
    private String description;

    // Проверяет, разрешен ли продукт на кето-диете.
    private boolean isAllowed() {
        return "ALLOWED".equalsIgnoreCase(category);
    }

    //Проверяет, ограничен ли продукт на кето-диете.
    private boolean isRestricted() {
        return "RESTRICTED".equalsIgnoreCase(category);
    }

    //Проверяет, запрещен ли продукт на кето-диете.
    private boolean isForbidden() {
        return "FORBIDDEN".equalsIgnoreCase(category);
    }

}
