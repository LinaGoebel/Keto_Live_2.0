package com.ketolive.service;

import com.ketolive.dto.AuthResponseDTO;
import com.ketolive.dto.LoginRequestDTO;
import com.ketolive.dto.RegisterRequestDTO;
import com.ketolive.model.User;
import com.ketolive.repository.UserRepository;
import com.ketolive.util.JwUtil;
import com.ketolive.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwUtil jwtUtil;

    //Регистрация
    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerRequest) {
        // Проверяем, что пользователь с таким email не существует
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }
// Создаем нового пользователя
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(PasswordUtil.hashPassword(registerRequest.getPassword()));
        user.setDateOfBirth(registerRequest.getDateOfBirth());
        user.setGender(registerRequest.getGender());
        user.setHeight(registerRequest.getHeight());
        user.setStartingWeight(registerRequest.getStartingWeight());
        user.setCurrentWeight(registerRequest.getStartingWeight());
        user.setTargetWeight(registerRequest.getTargetWeight());
        user.setPreferredFastingType(registerRequest.getPreferredFastingType());
        user.setRegistrationDate(LocalDateTime.now());

        // Сохраняем пользователя в базе данных, если указан начальный вес
        if (registerRequest.getStartingWeight() != null) {
            user.addWeightRecord(registerRequest.getStartingWeight(), "Starting weight");
        }

        // Сохраняем пользователя в базе данных
        User savedUser = userRepository.save(user);

        // Генерируем JWT токен
        String token = jwtUtil.generateToken(user.getEmail());

        // Возвращаем информацию о пользователе и JWT токене
        return new AuthResponseDTO(savedUser, token);
    }

    //Выполняет регистрацию пользователя
    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("User with email " + loginRequest.getEmail() + " not found."));
        if (PasswordUtil.checkPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }

        user.setLastLoginDate(LocalDateTime.now());

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDTO(user, token);

    }

    //получаем пользователя по email
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found."));
    }

    //Обновляет данные пользователя
    @Override
    public User updateUser(String email, User user) {
        User existingUser = getUserByEmail(email);

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(user.getDateOfBirth());
        }
        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }
        if (user.getHeight() != null) {
            existingUser.setHeight(user.getHeight());
        }

        if (user.getTargetWeight() != null) {
            existingUser.setTargetWeight(user.getTargetWeight());
        }

        if (user.getPreferredFastingType() != null) {
            existingUser.setPreferredFastingType(user.getPreferredFastingType());
        }

        return userRepository.save(existingUser);
    }

    //Обновляет вес пользователя.
    @Override
    public User updateWeight(String email, Double weight, String note) {
        User user = getUserByEmail(email);

        if (note != null && !note.isEmpty()) {
            user.addWeightRecord(weight, note);
        } else {
            user.addWeightRecord(weight);
        }
        return userRepository.save(user);
    }

    //Добавляет рецепт в избранное.
    @Override
    public User addFavoriteRecipe(String email, String recipeId) {
        User user = getUserByEmail(email);

        if (!user.getFavoriteRecipes().contains(recipeId)) {
            user.getFavoriteRecipes().add(recipeId);
            userRepository.save(user);
        }
        return user;
    }

    //Удаляет рецепт из избранного.
    @Override
    public User removeFavoriteRecipe(String email, String recipeId) {
        User user = getUserByEmail(email);

        user.getFavoriteRecipes().remove(recipeId);
        userRepository.save(user);
        return user;
    }

}