package com.ketolive.controller;

import com.ketolive.dto.CommentRequestDTO;
import com.ketolive.model.Recipe;
import com.ketolive.model.User;
import com.ketolive.service.RecipeService;
import com.ketolive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if ("DESC".equalsIgnoreCase(direction)) {
            sortDirection = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<Recipe> recipes = recipeService.getAllRecipes(pageable);

        return ResponseEntity.ok(recipes.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable String id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(
            @RequestBody Recipe recipe,
            @AuthenticationPrincipal UserDetails userDetails) {
        Recipe createdRecipe = recipeService.addRecipe(recipe, userDetails.getUsername());
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(
            @PathVariable String id,
            @RequestBody Recipe recipe,
            @AuthenticationPrincipal UserDetails userDetails) {
        Recipe updatedRecipe = recipeService.updateRecipe(id, recipe, userDetails.getUsername());
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(
            @PathVariable String id,
            @AuthenticationPrincipal UserDetails userDetails) {
        recipeService.deleteRecipe(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Recipe>> searchRecipes(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.searchRecipes(keyword, pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<Recipe>> getRecipesByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.getRecipesByCategory(category, pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<Page<Recipe>> getRecipesByTag(
            @PathVariable String tag,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.getRecipesByTag(tag, pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/my")
    public ResponseEntity<Page<Recipe>> getMyRecipes(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.getUserRecipes(userDetails.getUsername(), pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Recipe>> getUserRecipes(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.getUserRecipes(userId, pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/by-ingredients")
    public ResponseEntity<Page<Recipe>> findRecipesByIngredients(
            @RequestParam List<String> ingredients,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.findRecipesByIngredients(ingredients, pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/by-time/{maxTime}")
    public ResponseEntity<Page<Recipe>> getRecipesByMaxTime(
            @PathVariable Integer maxTime,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.getRecipesByMaxTime(maxTime, pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/by-difficulty/{difficultyLevel}")
    public ResponseEntity<Page<Recipe>> getRecipesByDifficultyLevel(
            @PathVariable String difficultyLevel,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.getRecipesByDifficultyLevel(difficultyLevel, pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/by-carbs/{maxNetCarbs}")
    public ResponseEntity<Page<Recipe>> getRecipesByMaxNetCarbs(
            @PathVariable Double maxNetCarbs,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.getRecipesByMaxNetCarbs(maxNetCarbs, pageable);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/popular")
    public ResponseEntity<Page<Recipe>> getPopularRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Recipe> recipes = recipeService.getPopularRecipes(pageable);
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("{id}/like")
    public ResponseEntity<Recipe> likeRecipe(@PathVariable String id) {
        Recipe recipe = recipeService.likeRecipe(id);
        return ResponseEntity.ok(recipe);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Recipe> addComment(
            @PathVariable String id,
            @RequestBody CommentRequestDTO commentRequest,
            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());

        Recipe recipe = recipeService.addComment(id, userDetails.getUsername(), user.getName(), commentRequest.getText());
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{recipeId}/comments/{commentId}")
    public ResponseEntity<Recipe> removeComment(
            @PathVariable String recipeId,
            @PathVariable String commentId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Recipe recipe = recipeService.removeComment(recipeId, commentId, userDetails.getUsername());
        return ResponseEntity.ok(recipe);
    }
}
