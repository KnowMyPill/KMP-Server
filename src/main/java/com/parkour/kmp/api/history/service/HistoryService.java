package com.parkour.kmp.api.history.service;

import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import org.springframework.data.domain.Page;

public interface HistoryService {
    void storeHistory(HistoryStoreRequest request);
    Page<History> findHistoriesByUser(String token, int page);
}
