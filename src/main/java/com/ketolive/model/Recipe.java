package com.ketolive.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;
    private String name;
    private String category;
    private List<String> ingredients;
}
