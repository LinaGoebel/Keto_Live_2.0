package com.ketolive.service;

import com.ketolive.model.Activity;

import java.util.List;

public interface ActivityService {
    Activity addActivity(Activity activity);

    List<Activity> getActivitiesByUserId(String userId);

}
