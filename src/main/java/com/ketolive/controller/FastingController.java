package com.ketolive.controller;


import com.ketolive.model.Fasting;
import com.ketolive.service.FastingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fasting")
public class FastingController {

    @Autowired
    private FastingService fastingService;

    @PostMapping("/start")
    public Fasting startFasting(@RequestBody Fasting fasting) {
        return fastingService.startFasting(fasting);
    }

    @PostMapping("/end")
    public Fasting endFasting(@RequestBody Fasting fasting) {
        return fastingService.endFasting(fasting);
    }
}
