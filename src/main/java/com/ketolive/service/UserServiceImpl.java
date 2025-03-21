package com.ketolive.service;

import com.ketolive.model.User;
import com.ketolive.repository.UserRepository;
import com.ketolive.util.JwUtil;
import com.ketolive.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwUtil jwtUtil;

    @Override
    public User register(User user) {
        user.setPassword(PasswordUtil.hashPassword(user.getPassword())); // Хешируем пароль
        return userRepository.save(user); // Сохраняем пользователя в базу данных
    }

    @Override
    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email); // Ищем пользователя по email
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (PasswordUtil.checkPassword(password, user.getPassword())) { // Проверяем пароль
                return jwtUtil.generateToken(user.getEmail()); // Генерируем JWT
            }
        }
        throw new RuntimeException("Invalid email or password"); // Если пользователь не найден или пароль неверный
    }
}