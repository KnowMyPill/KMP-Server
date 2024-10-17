package com.parkour.kmp.api.medication.service;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.medication.payload.response.MedicationResponse;

public interface MedicationService {
    MedicationResponse findMedicationByItemSeq(String itemSeq);
    MedicationResponse findMedicationByBarcode(String barcode) throws InvalidRequestException;
    Medication saveMedication(Medication medication);
    boolean existsByItemSeq(String itemSeq);
    Medication retrieveMedicationByItemSeq(String itemSeq)throws InvalidRequestException;
}
