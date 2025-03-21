package com.ketolive.service;

import com.ketolive.model.Activity;
import com.ketolive.model.Fasting;
import com.ketolive.repository.FastingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class FastingServiceImpl implements FastingService {

    @Autowired
    private FastingRepository fastingRepository;

    @Autowired
    private ActivityService activityService;

    @Override
    public Fasting startFasting(String userId) {
        Fasting fasting = new Fasting();
        fasting.setId(userId);
        fasting.setStartTime(LocalDateTime.now());
        fasting.setEndTime(null);
        fasting.setDuration(0);

        Activity activity = new Activity();
        activity.setUserId(userId);
        activity.setType("fasting");
        activity.setDescription("Started fasting");
        activityService.addActivity(activity);

        return fastingRepository.save(fasting);
    }

    @Override
    public Fasting endFasting(String userId) {
        Fasting fasting = fastingRepository.findByUserIdAndEndTimeIsNull(userId); // Находим активное голодание
        if (fasting != null) {
            fasting.setEndTime(LocalDateTime.now()); // Устанавливаем текущее время как время окончания голодания
            // Вычисляем продолжительность голодания в часах
            long duration = ChronoUnit.HOURS.between(fasting.getStartTime(), fasting.getEndTime());
            fasting.setDuration((int) duration); // Сохраняем продолжительность

            Activity activity = new Activity();
            activity.setUserId(userId);
            activity.setType("fasting");
            activity.setDescription("Ended fasting. Duration: " + duration + " hours");
            activityService.addActivity(activity);

            return fastingRepository.save(fasting); // Сохраняем обновленную запись в базу данных
        }
        throw new RuntimeException("No active fasting found for user"); // Если активное голодание не найдено
    }

    @Override
    public List<Fasting> getFastingHistory(String userId) {
        return fastingRepository.findByUserId(userId); // Получаем историю голодания для пользователя
    }
}
