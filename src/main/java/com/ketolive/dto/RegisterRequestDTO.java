package com.ketolive.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

//DTO для запроса регистрации нового пользователя
@Data
public class RegisterRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private LocalDate dateOfBirth;
    private String gender;
    private Double height;
    private Double startingWeight;
    private Double currentWeight;
    private Double targetWeight;
    private String preferredFastingType;
}

