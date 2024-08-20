package com.parkour.kmp.api.medcode.service;

import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;

public interface MedCodeService {
    void updateMedCodeData(MedCodeSummaryResponse medCodeSummaryResponse);
}
