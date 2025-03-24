package com.ketolive.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "fastings")
public class Fasting {

    @Id
    private String id;

    // Добавляем поле userId
    private String userId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer duration;// в часах
}
