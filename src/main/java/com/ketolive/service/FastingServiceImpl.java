package com.ketolive.service;

import com.ketolive.exeption.ResourceNotFoundException;
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

    @Override
    public Fasting startFasting(Fasting fasting, String userId) {
        Fasting activeFasting = fastingRepository.findByUserIdAndEndTimeIsNull(userId); // Находим активное голодание
        if (activeFasting != null) {
            endFasting(activeFasting.getId(), userId);
        }

        // Устанавливаем данные для нового голодания
        fasting.setUserId(userId);
        fasting.setStartTime(LocalDateTime.now());
        fasting.setEndTime(null);
        fasting.setDuration(0);

        // Сохраняем в базу данных
        return fastingRepository.save(fasting);
    }

    @Override
    public Fasting endFasting(String fastingId, String userId) {
        // Находим активное голодание по ID
        Fasting fasting = fastingRepository.findById(fastingId)
                .orElseThrow(() -> new ResourceNotFoundException("Голодание с ID " + fastingId + " не найдено"));
        //Проверяем, принадлежит ли голодание указанному пользователю
        if (!fasting.getUserId().equals(userId)) {
            throw new ResourceNotFoundException("Голодание с ID " + fastingId + " не найдено.");
        }
        if (fasting.getEndTime() != null) {
            return fasting;
        }
        //Устанавливаем время окончания и рассчитываем продолжительность
        LocalDateTime endTime = LocalDateTime.now();
        fasting.setEndTime(endTime);

        long durationHours = ChronoUnit.HOURS.between(fasting.getStartTime(), endTime);
        fasting.setDuration((int) durationHours);

        return fastingRepository.save(fasting);
    }

    @Override
    public Fasting getActiveFasting(String userId) {
        // Ищем голодание, у которого userId совпадает с переданным
        //  и поле endTime равно null (голодание не завершено)
        return fastingRepository.findByUserIdAndEndTimeIsNull(userId);

    }


    //Получает активное голодание пользователя (если есть)
    @Override
    public List<Fasting> getUserFastings(String userId) {
        return fastingRepository.findByUserId(userId);

    }
}
