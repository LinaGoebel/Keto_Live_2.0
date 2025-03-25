package com.ketolive.repository;

import com.ketolive.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByNameContainingIgnoreCase(String name); // Поиск рецептов по названию

    // Возвращает список рецептов по названию (по страницам)
    Page<Recipe> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // нахождение рецептов по названию или описанию (по страницам)
    Page<Recipe> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String keyword, String keyword2, Pageable pageable);

    // Возвращает список рецептов по категории
    Page<Recipe> findByCategory(String category, Pageable pageable);

    // Возвращает список рецептов по тегу
    Page<Recipe> findByTagsContaining(String tag, Pageable pageable);

    // Возвращает список рецептов по автору
    Page<Recipe> findByAuthorId(String authorId, Pageable pageable);

    // Находит рецепты, содержащие указанные ингредиенты.
    Page<Recipe> findByIngredientsIn(List<String> ingredients, Pageable pageable);

    //Находит рецепты с временем приготовления не больше указанного.
    Page<Recipe> findByCookingTimeLessThanEqual(Integer cookingTime, Pageable pageable);

    //Находит рецепты с определенным уровнем сложности.
    Page<Recipe> findByDifficultyLevel(String difficultyLevel, Pageable pageable);

    //Находит рецепты с чистыми углеводами на порцию не больше указанного значения.
    @Query("{'nutritionPerServing.netCarbs': {$lte: ?0}}")
    Page<Recipe> findByNetCarbsLessThanEqual(Double maxNetCarbs, Pageable pageable);

    //Находит самые популярные рецепты (с наибольшим количеством лайков)
    Page<Recipe> findTopByOrderByLikesDesc(Pageable pageable);
}
