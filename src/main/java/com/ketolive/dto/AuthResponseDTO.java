package com.ketolive.dto;

import com.ketolive.model.User;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private String id;
    private String name;
    private String email;
    private String token;

    public AuthResponseDTO(User user, String token) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.token = token;
    }
}
