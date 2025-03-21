package com.ketolive.repository;

import com.ketolive.model.Fasting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FastingRepository extends MongoRepository<Fasting, String> {
    List<Fasting> findByUserId(String userId); // Найти все записи о голодании для конкретного пользователя
    Fasting findByUserIdAndEndTimeIsNull(String userId); // Найти активное голодание для пользователя
}
