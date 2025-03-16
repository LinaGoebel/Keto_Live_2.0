package com.ketolive.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String category;//какие продукты можно съесть, какие нельзя и какие редко
}
