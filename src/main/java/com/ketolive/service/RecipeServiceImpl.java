package com.ketolive.service;

import com.ketolive.exeption.ResourceNotFoundException;
import com.ketolive.exeption.UnauthorizedException;
import com.ketolive.model.Recipe;
import com.ketolive.model.Recipe.Comment;
import com.ketolive.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Override
    public Recipe getRecipeById(String id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Рецепт с ID " + id + " не найден"));
    }

    @Override
    public Recipe addRecipe(Recipe recipe, String userId) {
        recipe.setAuthorId(userId);
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setLikes(0);
        recipe.setComments(List.of());

        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(String id, Recipe recipe, String userId) {
        Recipe existingRecipe = getRecipeById(id);

        if (!existingRecipe.getAuthorId().equals(userId)) {
            throw new UnauthorizedException("Только автор может редактировать рецепт");
        }
        // Обновляем поля рецепта
        existingRecipe.setName(recipe.getName());
        existingRecipe.setCategory(recipe.getCategory());
        existingRecipe.setDescription(recipe.getDescription());
        existingRecipe.setIngredients(recipe.getIngredients());
        existingRecipe.setInstructions(recipe.getInstructions());
        existingRecipe.setCookingTime(recipe.getCookingTime());
        existingRecipe.setServings(recipe.getServings());
        existingRecipe.setNutritionPerServing(recipe.getNutritionPerServing());
        existingRecipe.setImageUrl(recipe.getImageUrl());
        existingRecipe.setDifficultyLevel(recipe.getDifficultyLevel());
        existingRecipe.setTags(recipe.getTags());

        return recipeRepository.save(existingRecipe);
    }

    @Override
    public void deleteRecipe(String id, String userId) {
        Recipe recipe = getRecipeById(id);

        if (!recipe.getAuthorId().equals(userId)) {
            throw new UnauthorizedException("Только автор может удалить рецепт");
        }
        recipeRepository.delete(recipe);
    }

    @Override
    public Page<Recipe> searchRecipes(String keyword, Pageable pageable) {
        return recipeRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                keyword, keyword, pageable);
    }

    @Override
    public Page<Recipe> getRecipesByCategory(String category, Pageable pageable) {
        return recipeRepository.findByCategory(category, pageable);
    }

    @Override
    public Page<Recipe> getRecipesByTag(String tag, Pageable pageable) {
        return recipeRepository.findByTagsContaining(tag, pageable);
    }

    @Override
    public Page<Recipe> getUserRecipes(String userId, Pageable pageable) {
        return recipeRepository.findByAuthorId(userId, pageable);
    }

    @Override
    public Page<Recipe> findRecipesByIngredients(List<String> ingredients, Pageable pageable) {
        return recipeRepository.findByIngredientsIn(ingredients, pageable);
    }

    @Override
    public Page<Recipe> getRecipesByMaxTime(Integer maxTime, Pageable pageable) {
        return recipeRepository.findByCookingTimeLessThanEqual(maxTime, pageable);
    }

    @Override
    public Page<Recipe> getRecipesByDifficultyLevel(String difficultyLevel, Pageable pageable) {
        return recipeRepository.findByDifficultyLevel(difficultyLevel, pageable);
    }

    @Override
    public Page<Recipe> getRecipesByMaxNetCarbs(Double maxNetCarbs, Pageable pageable) {
        return recipeRepository.findByNetCarbsLessThanEqual(maxNetCarbs, pageable);
    }

    @Override
    public Page<Recipe> getPopularRecipes(Pageable pageable) {
        return recipeRepository.findTopByOrderByLikesDesc(pageable);
    }

    @Override
    public Recipe likeRecipe(String id) {
        Recipe recipe = getRecipeById(id);
        recipe.addLike();
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe addComment(String id, String userId, String userName, String text) {
        Recipe recipe = getRecipeById(id);
        recipe.addComment(userId, userName, text);
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe removeComment(String recipeId, String commentId, String userId) {
        Recipe recipe = getRecipeById(recipeId);

        //нахождение комментария
        Comment comment = recipe.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Комментарий с ID " + commentId + " не найден."));

        //проверка автора комментария
        if (!comment.getUserId().equals(userId) && !recipe.getAuthorId().equals(userId)) {
            throw new UnauthorizedException("Только автор или автор рецепта может удалить комментарии");
        }

        recipe.setComments(
                recipe.getComments().stream()
                        .filter(c -> !c.getId().equals(commentId))
                        .collect(Collectors.toList())
        );
        return recipeRepository.save(recipe);
    }
}
