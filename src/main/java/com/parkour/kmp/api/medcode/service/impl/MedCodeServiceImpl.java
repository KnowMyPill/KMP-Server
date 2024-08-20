package com.parkour.kmp.api.medcode.service.impl;

import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.medcode.repository.MedCodeRepository;
import com.parkour.kmp.api.medcode.service.MedCodeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedCodeServiceImpl implements MedCodeService {

    private final MedCodeRepository medCodeRepository;

    @Override
    @Transactional
    public void updateMedCodeData(MedCodeSummaryResponse medCodeSummaryResponse) {
        medCodeRepository.deleteAll();
        //TODO: mapping 후 save
        // medCodeRepository.saveAll(medCodeSummaryResponse.getData());
    }
}
