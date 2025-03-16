package com.ketolive.service;

import com.ketolive.model.User;

public interface UserService {
    User register(User user);
    String login(String email, String password);
}
