package com.ketolive.controller;

import com.ketolive.model.Fasting;
import com.ketolive.service.FastingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fasting")
public class FastingController {

    @Autowired
    private FastingService fastingService; // Сервис для работы с голоданием


    @PostMapping("/start")
    public ResponseEntity<Fasting> startFasting(
            @RequestBody Fasting fasting, @AuthenticationPrincipal UserDetails userDetails) {
        Fasting createdFasting = fastingService.startFasting(fasting, userDetails.getUsername());
        return new ResponseEntity<>(createdFasting, HttpStatus.CREATED);

    }


    @PostMapping("/{fastingId}/end")
    public ResponseEntity<Fasting> endFasting(@RequestBody String fastingId,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        Fasting endedFasting = fastingService.endFasting(fastingId, userDetails.getUsername());
        return ResponseEntity.ok(endedFasting);
    }

    @GetMapping("/active")
    public ResponseEntity<Fasting> getActiveFasting(@AuthenticationPrincipal UserDetails userDetails) {
        Fasting activeFasting = fastingService.getActiveFasting(userDetails.getUsername());

        if (activeFasting == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(activeFasting);
    }

    @GetMapping()
    public ResponseEntity<List<Fasting>> getUserFastings(@AuthenticationPrincipal UserDetails userDetails) {
        List<Fasting> fastings = fastingService.getUserFastings(userDetails.getUsername());
        return ResponseEntity.ok(fastings);
    }
}
