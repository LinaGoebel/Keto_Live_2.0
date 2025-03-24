package com.ketolive.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "activities")
public class Activity {

    @Id
    private String id;
    private String userId;
    private String type;
    private String description;
    private LocalDateTime timestamp;
}
