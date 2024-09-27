package com.parkour.kmp.api.medication.service;

import com.parkour.kmp.api.medication.payload.response.MedicationResponse;

public interface MedicationService {
    MedicationResponse findMedicationByItemSeq(String itemSeq);
    MedicationResponse findMedicationByBarcode(String barcode);
    void saveMedication(MedicationResponse medicationResponse);
}
