package com.ketolive.repository;

import com.ketolive.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByUserId(String userId); // Находим все активности для конкретного пользователя>
}
