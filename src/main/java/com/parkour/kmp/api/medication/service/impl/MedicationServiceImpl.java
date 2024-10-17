package com.parkour.kmp.api.medication.service.impl;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.invoker.MedDataApiInvoker;
import com.parkour.kmp.api.medcode.domain.MedCode;
import com.parkour.kmp.api.medcode.service.MedCodeService;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.medication.payload.response.MedicationResponse;
import com.parkour.kmp.api.medication.repository.MedicationRepository;
import com.parkour.kmp.api.medication.service.MedicationService;
import com.parkour.kmp.api.client.payload.response.meddata.MedItemApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private final MedCodeService medCodeService;
    private final MedDataApiInvoker invoker;
    private final MedicationRepository medicationRepository;

    @Override
    public MedicationResponse findMedicationByBarcode(String barcode) throws InvalidRequestException {
        String processedCode = processCode(barcode);
        MedCode medCode = medCodeService.findMedCodeByCodeStandard(processedCode);

        MedItemApiResponse medItemApiResponse = fetchMedicationData(medCode);

        return buildMedicationResponse(medItemApiResponse);
    }

    @Override
    public Medication saveMedication(Medication medication){
        return medicationRepository.save(medication);
    }

    @Override
    public boolean existsByItemSeq(String itemSeq) {
        return medicationRepository.existsByItemSeq(itemSeq);
    }

    @Override
    public Medication retrieveMedicationByItemSeq(String itemSeq) throws InvalidRequestException {
        return medicationRepository.findByItemSeq(itemSeq)
                .orElseThrow(() -> new InvalidRequestException("Medication does not exist for given item sequence."));
    }

    @Override
    public MedicationResponse findMedicationByItemSeq(String itemSeq) {
        MedItemApiResponse item = invoker.fetchMedicationData(itemSeq);
        return buildMedicationResponse(item);
    }

    private MedItemApiResponse fetchMedicationData(MedCode medCode) {
        try {
            return invoker.fetchMedicationData(medCode.getItemSeq());
        } catch (Exception e) {
            throw new IllegalArgumentException("Medication does not exist in e약은요 database.", e);
        }
    }

    private MedicationResponse buildMedicationResponse(MedItemApiResponse medItemApiResponse) {
        if (medItemApiResponse == null) {
            throw new IllegalArgumentException("Medication does not exist for given item sequence");
        }
        return new MedicationResponse(
                medItemApiResponse.getItemSeq(),
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

    private String processCode(String barcode) {
        barcode = barcode.trim();
        return barcode.length() > 13 ? barcode.substring(1) : barcode;
    }
}
