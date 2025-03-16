package com.ketolive.model;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "fastings")
public class Fasting {

    @Id
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer duration;// в часах
}
