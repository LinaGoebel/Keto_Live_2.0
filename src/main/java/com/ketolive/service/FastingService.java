package com.ketolive.service;

import com.ketolive.model.Fasting;

import java.util.List;

public interface FastingService {
    //Начинает новое голодание для пользователя.
    Fasting startFasting(Fasting fastingId, String userId);

    //Завершает голодание пользователя.
    Fasting endFasting(String fastingId, String userId);

    // Получает активное голодание пользователя (если есть).
    Fasting getActiveFasting(String userId);

    //Получает все записи о голодании пользователя.
    List<Fasting> getUserFastings(String userId);
}
