package com.parkour.kmp.api.history.service;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import com.parkour.kmp.api.history.payload.response.HistoryResponse;

import java.util.List;

public interface HistoryService {
    void storeHistory(HistoryStoreRequest request) throws InvalidRequestException;
    List<HistoryResponse> findHistoriesByUser(String token, int page);
}
