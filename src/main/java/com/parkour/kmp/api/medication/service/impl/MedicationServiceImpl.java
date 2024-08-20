package com.parkour.kmp.medication.service;

import com.parkour.kmp.api.domain.MedicationApiInvokerCommand;
import com.parkour.kmp.api.invoker.MedicationApiInvoker;
import com.parkour.kmp.api.invoker.MedicationApiInvokerFactory;
import com.parkour.kmp.api.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.payload.response.MedicationApiResponse;
import com.parkour.kmp.medication.payload.response.MedicationResponse;
import com.parkour.kmp.medication.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final MedicationApiInvokerFactory apiInvokerFactory;

    public MedicationResponse findMedicationByBarcode(String barcode) {
        // TODO: 바코드 값을 의약품표준코드로 맵핑한다

        MedicationApiInvoker invoker = apiInvokerFactory.createMedicationApiInvoker(MedicationApiInvokerCommand.GET_MED_FROM_CODE);
        try {
            MedicationApiResponse medicationApiResponse = invoker.fetchMedicationData(barcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // MedicationApiResponse medicationApiResponse = apiInvoker.fetchMedicationData(medicationCode);
        return new MedicationResponse("", "", "", "", "", "", "", "");
    }

    public void saveMedication(MedicationResponse medicationResponse) {
        // TODO: map MedicationResponse to Medication entity & then save to repository
        // medicationRepository.save(medicationResponse);
    }


}
