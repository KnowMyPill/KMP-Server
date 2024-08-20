package com.parkour.kmp.api.medcode.service.impl;

import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.medcode.repository.MedCodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedCodeServiceImpl {
    private final MedCodeRepository medCodeRepository;

    @Transactional
    public void updateMedCodeData(MedCodeSummaryResponse medCodeSummaryResponse) {
        medCodeRepository.deleteAll();
        medCodeRepository.saveAll(medCodeSummaryResponse.getData());
    }
}
