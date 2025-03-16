package com.ketolive.service;

import com.ketolive.model.User;
import com.ketolive.repository.UserRepository;
import com.ketolive.util.JwUtil;
import com.ketolive.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwUtil jwtUtil;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            return jwtUtil.generateToken(email);
        }
        return "Ошибка авторизации";
    }
}
