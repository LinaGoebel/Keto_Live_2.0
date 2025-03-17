package com.ketolive.service;

import com.ketolive.model.Fasting;

public interface FastingService {
    Fasting startFasting(Fasting fasting);
    Fasting endFasting(Fasting fasting);
}
