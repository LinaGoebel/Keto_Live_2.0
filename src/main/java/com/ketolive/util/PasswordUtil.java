package com.ketolive.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean checkPassword(String rawPassword, String hashPassword) {
        return encoder.matches(rawPassword, hashPassword);
    }
}
