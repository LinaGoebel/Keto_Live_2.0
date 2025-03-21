package com.ketolive.service;

import com.ketolive.model.Fasting;

import java.util.List;

public interface FastingService {
    Fasting startFasting(String userId);

    Fasting endFasting(String userId);

    List<Fasting> getFastingHistory(String userId);
}
