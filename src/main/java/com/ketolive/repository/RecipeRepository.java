package com.ketolive.repository;

import com.ketolive.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
}
