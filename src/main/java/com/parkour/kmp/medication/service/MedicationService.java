package com.parkour.kmp.medication.service;

import com.parkour.kmp.api.invoker.MedicationApiInvoker;
import com.parkour.kmp.medication.payload.response.MedicationResponse;
import com.parkour.kmp.medication.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final MedicationApiInvoker apiInvoker;

    public MedicationResponse findMedicationByBarcode(String barcode) {
        // TODO: 바코드 값을 의약품표준코드로 맵핑한다
        MedicationApiResponse medicationApiResponse = apiInvoker.fetchMedicationData(medicationCode);
    }
}
