package com.parkour.kmp.api.medication.service.impl;

import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.invoker.MedDataApiInvoker;
import com.parkour.kmp.api.medication.payload.response.MedicationResponse;
import com.parkour.kmp.api.medication.repository.MedicationRepository;
import com.parkour.kmp.api.medication.service.MedicationService;
import com.parkour.kmp.api.client.payload.response.meddata.MedItemApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;
    private final MedDataApiInvoker invoker;

    @Override
    public MedicationResponse findMedicationByBarcode(String barcode) {
        // TODO: 바코드 값을 의약품표준코드로 맵핑한다

        MedItemApiResponse medItemApiResponse = null;
        try {
            medItemApiResponse = invoker.fetchMedicationData(barcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // MedicationApiResponse medicationApiResponse = apiInvoker.fetchMedicationData(medicationCode);
        return new MedicationResponse("", "", "", "", "", "", "", "");
    }

    @Override
    public void saveMedication(MedicationResponse medicationResponse) {
        // TODO: map MedicationResponse to Medication entity & then save to repository
        // medicationRepository.save(medicationResponse);
    }

    @Override
    public MedicationResponse findMedicationByItemSeq(String itemSeq) {
        MedItemApiResponse item = invoker.fetchMedicationData(itemSeq);
        return new MedicationResponse(
                item.getItemName(),
                item.getEfcyQesitm(),
                item.getUseMethodQesitm(),
                item.getAtpnWarnQesitm(),
                item.getAtpnQesitm(),
                item.getIntrcQesitm(),
                item.getSeQesitm(),
                item.getDepositMethodQesitm()
        );
    }
}
