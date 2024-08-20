package com.parkour.kmp.api.client.payload.response;

import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MedCodeSummaryResponse {
    private int perPage;
    private int totalCount;
    private int currentCount;
    private LocalDateTime fetchedAt;
    private List<MedCodeApiResponse> data;

    public MedCodeSummaryResponse(int perPage, int totalCount, int currentCount, List<MedCodeApiResponse> data) {
        this.perPage = perPage;
        this.totalCount = totalCount;
        this.currentCount = currentCount;
        this.fetchedAt = LocalDateTime.now();
        this.data = data;
    }
}
