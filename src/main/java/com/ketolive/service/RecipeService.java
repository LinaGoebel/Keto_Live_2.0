package com.ketolive.service;

import com.ketolive.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();

    Recipe addRecipe(Recipe recipe);
}
