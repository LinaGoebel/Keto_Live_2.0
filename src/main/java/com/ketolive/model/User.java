package com.ketolive.model;

import com.ketolive.util.PasswordUtil;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // Генерирует геттеры, сеттеры и т.д.
@Document(collection = "users") // Сопоставляет класс с коллекцией "users"
public class User {

    @Id // Поле "id" будет уникальным идентификатором (_id в MongoDB)
    private String id;
    private String name;
    private String email;
    private String password;

}
