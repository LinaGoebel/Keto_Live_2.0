package com.ketolive.service;

import com.ketolive.dto.AuthResponseDTO;
import com.ketolive.dto.LoginRequestDTO;
import com.ketolive.dto.RegisterRequestDTO;
import com.ketolive.model.User;

public interface UserService {
    // Выполняет регистрацию пользователя
    AuthResponseDTO register(RegisterRequestDTO registerRequest);

    // Выполняет вход пользователя
    AuthResponseDTO login(LoginRequestDTO loginRequest);

    // Получает пользователя по email
    User getUserByEmail(String email);

    // Обновляет данные пользователя
    User updateUser(String email, User user);

    // Обновляет вес пользователя
    User updateWeight(String email, Double weight, String note);

    // Добавляет рецепт в избранное
    User addFavoriteRecipe(String email, String recipeId);

    // Удаляет рецепт из избранного
    User removeFavoriteRecipe(String email, String recipeId);

}
