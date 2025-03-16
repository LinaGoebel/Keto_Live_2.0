package com.ketolive.repository;

import com.ketolive.model.Fasting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FastingRepository extends MongoRepository<Fasting, String> {
}
