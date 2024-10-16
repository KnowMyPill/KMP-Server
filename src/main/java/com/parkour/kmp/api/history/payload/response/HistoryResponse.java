package com.parkour.kmp.api.history.payload.response;

import com.parkour.kmp.api.medication.payload.response.MedicationResponse;
import lombok.Getter;

@Getter
public class HistoryResponse {
    String userId;
    MedicationResponse medication;

    protected HistoryResponse() {}

    public HistoryResponse(String userId, MedicationResponse medication) {
        this.userId = userId;
        this.medication = medication;
    }
}
