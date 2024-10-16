package com.parkour.kmp.api.medication.service.impl;

import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.invoker.MedDataApiInvoker;
import com.parkour.kmp.api.medcode.domain.MedCode;
import com.parkour.kmp.api.medcode.service.MedCodeService;
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
    private final MedCodeService medCodeService;
    private final MedDataApiInvoker invoker;

    @Override
    public MedicationResponse findMedicationByBarcode(String barcode) {
        // TODO: 바코드 값을 의약품표준코드로 맵핑한다

        MedItemApiResponse medItemApiResponse = null;
        try {
            String code = processCode(barcode);
            MedCode medcode = medCodeService.findMedCodeByCodeStandard(code);
            medItemApiResponse = invoker.fetchMedicationData(medcode.getItemSeq());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: Save to history
        return new MedicationResponse(
                medItemApiResponse.getItemName(),
                medItemApiResponse.getEfcyQesitm(),
                medItemApiResponse.getUseMethodQesitm(),
                medItemApiResponse.getAtpnWarnQesitm(),
                medItemApiResponse.getAtpnQesitm(),
                medItemApiResponse.getIntrcQesitm(),
                medItemApiResponse.getSeQesitm(),
                medItemApiResponse.getDepositMethodQesitm()
        );
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

    private String processCode(String barcode) {
        barcode = barcode.trim();
        if (barcode.startsWith("88")) {
            return barcode.substring(1);
        }
        return barcode;
    }
}
