package com.ketolive.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@Data
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;
    private String name;
    private String category;
    //описание рецепта
    private String description;

    //список ингредиентов
    private List<String> ingredients = new ArrayList<>();

    //шаги приготовления
    private List<String> instructions = new ArrayList<>();

    //время приготовления
    private Integer cookingTime;

    //количество порций
    private Integer servings;

    //информация о питательности
    private NutritionInfo nutritionPerServing;

    //Url изображения рецепта
    private String imageUrl;

    //Уровень сложности
    private String difficultyLevel;

    //теги рецепта
    private List<String> tags = new ArrayList<>();

    //id автора
    private String authorId;

    //дата создания рецепта
    private LocalDateTime createdAt = LocalDateTime.now();

    //количество лайков
    private Integer likes = 0;

    //список комментариев
    private List<Comment> comments = new ArrayList<>();

    //класс для хранения информации о питательности
    @Data
    public static class NutritionInfo {
        private Double calories;
        private Double protein;
        private Double fat;
        private Double carbs;
        private Double fiber;
        private Double netCarbs;

        //
        public Double getNetCarbs() {
            if (netCarbs != null) {
                return netCarbs;
            }
            if (carbs != null && fiber != null) {
                return Math.max(0, carbs - fiber);
            }
            return null;
        }
    }

    //класс для хранения комментария
    @Data
    public static class Comment {
        private String id;
        private String userId;
        private String userName;
        private String text;
        private LocalDateTime createdAt = LocalDateTime.now();
    }

    // Метод для добавления лайка
    public void addLike() {
        if (likes == null) {
            likes = 0;
        }
        likes++;
    }

    // Метод для добавления комментария
    public Comment addComment(String userId, String userName, String text) {
        Comment comment = new Comment();
        comment.setId(randomUUID().toString());
        comment.setUserId(userId);
        comment.setUserName(userName);
        comment.setText(text);

        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);

        return comment;
    }
}
