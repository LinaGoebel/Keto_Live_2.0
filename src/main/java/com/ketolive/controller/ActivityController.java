package com.ketolive.controller;

import com.ketolive.model.Activity;
import com.ketolive.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public Activity addActivity(@RequestBody Activity activity) {
        return activityService.addActivity(activity);
    }

    @GetMapping("/user/{userId}")
    public List<Activity> getActivitiesByUserId(@PathVariable String userId) {
        return activityService.getActivitiesByUserId(userId);
    }
}
