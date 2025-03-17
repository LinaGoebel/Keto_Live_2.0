package com.ketolive.service;

import com.ketolive.model.Fasting;
import com.ketolive.repository.FastingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class FastingServiceImpl implements FastingService {

    @Autowired
    private FastingRepository fastingRepository;

    @Override
    public Fasting startFasting(Fasting fasting) {
        fasting.setStartTime(LocalDateTime.now());
        fasting.setEndTime(null);
        fasting.setDuration(0);
        return fastingRepository.save(fasting);
    }

    @Override
    public Fasting endFasting(Fasting fasting) {
        fasting.setEndTime(LocalDateTime.now());
        long duration = ChronoUnit.HOURS.between(fasting.getStartTime(), fasting.getEndTime());
        fasting.setDuration((int) duration);
        return fastingRepository.save(fasting);
    }
}
