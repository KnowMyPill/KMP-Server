package com.parkour.kmp.api.history.service;

import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.user.domain.User;
import org.springframework.data.domain.Page;

public interface HistoryService {
    void storeHistory(User user, Medication medication);
    Page<History> findHistoriesByUser(User user, int page);
}
