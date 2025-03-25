package com.ketolive.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
    private String fastingType;// вечерняя, утренняя

    //Проверяет, активно ли голодание (не завершено).
    public boolean isActive() {
        return endTime == null;
    }

    //Рассчитывает текущую продолжительность голодания в часах.
    public long getCurrentDurationHours() {
        if (endTime != null) {
            return duration;
        }
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.HOURS.between(startTime, now);
    }

    //Рассчитывает текущую продолжительность голодания в минутах.
    public long getCurrentDurationMinutes() {
        if (endTime != null) {
            return duration * 60;
        }
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.MINUTES.between(startTime, now);
    }

    //Рассчитывает прогресс голодания в процентах относительно целевой продолжительности.
    public double getProgressPercentage(int targetHours) {
        long currentDuration = getCurrentDurationHours();
        return Math.min(100, (currentDuration * 100) / targetHours);
    }
}
