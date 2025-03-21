package com.ketolive.service;

import com.ketolive.model.Activity;
import com.ketolive.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity addActivity(Activity activity) {
        activity.setTimestamp(LocalDateTime.now());
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> getActivitiesByUserId(String userId) {
        return activityRepository.findByUserId(userId);
    }
}
