package com.ketolive.controller;

import com.ketolive.model.Fasting;
import com.ketolive.model.User;
import com.ketolive.repository.UserRepository;
import com.ketolive.service.FastingService;
import com.ketolive.util.JwUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fasting")
public class FastingController {

    @Autowired
    private FastingService fastingService; // Сервис для работы с голоданием

    @Autowired
    private JwUtil jwtUtil; // Сервис для работы с JWT
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/start")
    public Fasting startFasting(@RequestHeader("Authorization") String token) {
        String userId = getUserIdFromToken(token); // Получаем ID пользователя из токена
        return fastingService.startFasting(userId); // Начинаем голодание
    }

    @PostMapping("/end")
    public Fasting endFasting(@RequestHeader("Authorization") String token) {
        String userId = getUserIdFromToken(token); // Получаем ID пользователя из токена
        return fastingService.endFasting(userId); // Завершаем голодание
    }

    @GetMapping("/history")
    public List<Fasting> getFastingHistory(@RequestHeader("Authorization") String token) {
        String userId = getUserIdFromToken(token); // Получаем ID пользователя из токена
        return fastingService.getFastingHistory(userId); // Получаем историю голодания
    }

    private String getUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Убираем "Bearer " из токена
            Claims claims = jwtUtil.parseToken(token); // Парсим токен
            String email = claims.getSubject(); // Извлекаем email из токена
            Optional<User> userOptional = userRepository.findByEmail(email);// Ищем пользователя по email
            if (userOptional.isPresent()) {
                return userOptional.get().getId(); // Возвращаем ID пользователя
            } else {
                throw new RuntimeException("User not found"); // Если пользователь не найден, выбрасываем исключение
            }
        }
        throw new RuntimeException("Invalid token"); // Если токен невалиден, выбрасываем исключение
    }
}
