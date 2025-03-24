package com.ketolive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data // Генерирует геттеры, сеттеры и т.д.
@Document(collection = "users") // Сопоставляет класс с коллекцией "users"
public class User {

    @Id // Поле "id" будет уникальным идентификатором (_id в MongoDB)
    private String id;

    @NotBlank(message = "Name is required")// Проверяет, что поле "name" не пустое
    private String name;

    @Email(message = "Invalid email address")// Проверяет, что поле "email" является действительным email-адресом
    @NotBlank(message = "Email is required")
    @Indexed(unique = true)// создаёт индекс в MongoDB для быстрого поиска и обеспечивает уникальность email
    private String email;

    @JsonIgnore //указывает, что поле не должно быть сериализовано в JSON
    private String password;

    private LocalDate dateOfBirth;
    private String gender;
    private Double height;
    private Double startingWeight;
    private Double currentWeight;//текущий вес
    private Double targetWeight;// целевой вес

    //история изменения веса пользователя
    private List<WeightRecord> weightHistory = new ArrayList<>();

    //предпочтения типа фастинга
    private String preferredFastingType;

    //список ID избранных рецептов
    private List<String> favoriteRecipes = new ArrayList<>();

    //дата регистрации
    private LocalDateTime registrationDate = LocalDateTime.now();

    //дата последнего входа
    private LocalDateTime lastLoginDate;

    //Запись изменения веса
    @Data
    public static class WeightRecord {
        private LocalDate date;
        private Double weight;
        private String note;

        private WeightRecord(LocalDate date, Double weight) {
            this.date = date;
            this.weight = weight;
        }

        public WeightRecord(LocalDate date, Double weight, String note) {
            this.date = date;
            this.weight = weight;
            this.note = note;
        }
    }

    //Добавляет запись о весе в историю
    public void addWeightRecord(Double weight) {
        this.currentWeight = weight;
        this.weightHistory.add(new WeightRecord(LocalDate.now(), weight));
    }

    //Добавляет запись о весе с примечаниями в историю
    public void addWeightRecord(Double weight, String note) {
        this.currentWeight = weight;
        this.weightHistory.add(new WeightRecord(LocalDate.now(), weight, note));
    }
}
