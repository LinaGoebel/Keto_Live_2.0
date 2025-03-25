package com.ketolive.service;

import com.ketolive.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeService {
    // Получение всех рецептов
    Page<Recipe> getAllRecipes(Pageable pageable);

    // Получение рецепта по id
    Recipe getRecipeById(String id);

    // Добавление рецепта
    Recipe addRecipe(Recipe recipe, String userId);

    // Обновление рецепта
    Recipe updateRecipe(String id, Recipe recipe, String userId);

    // Удаление рецепта
    void deleteRecipe(String id, String userId);

    // Ищет рецепты по ключевому слову в названии или описании.
    Page<Recipe> searchRecipes(String keyword, Pageable pageable);

    //Получает рецепты определенной категории
    Page<Recipe> getRecipesByCategory(String category, Pageable pageable);

    //Получает рецепты с определенным тегом.
    Page<Recipe> getRecipesByTag(String tag, Pageable pageable);

    //Получает рецепты, созданные указанным пользователем
    Page<Recipe> getUserRecipes(String userId, Pageable pageable);

    //Получает рецепты, содержащие указанные ингредиенты
    Page<Recipe> findRecipesByIngredients(List<String> ingredients, Pageable pageable);

    // Получает рецепты с временем приготовления не больше указанного
    Page<Recipe> getRecipesByMaxTime(Integer maxTime, Pageable pageable);

    //Получает рецепты с определенным уровнем сложности
    Page<Recipe> getRecipesByDifficultyLevel(String difficultyLevel, Pageable pageable);

    // Получает рецепты с чистыми углеводами на порцию не больше указанного значения.
    Page<Recipe> getRecipesByMaxNetCarbs(Double maxNetCarbs, Pageable pageable);

    //Получает самые популярные рецептыПолучает самые популярные рецепты
    Page<Recipe> getPopularRecipes(Pageable pageable);

    //Добавляет лайк к рецепту
    Recipe likeRecipe(String id);

    //добавляет комментарий к рецепту
    Recipe addComment(String id, String userId, String userName, String text);

    //удаляет комментарий к рецепту
    Recipe removeComment(String recipeId, String commentId, String userId);
}
