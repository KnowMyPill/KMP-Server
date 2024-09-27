package com.parkour.kmp.api.medication.controller;

import com.parkour.kmp.api.medication.payload.response.MedicationResponse;
import com.parkour.kmp.api.medication.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/medication")
@RequiredArgsConstructor
public class MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    public ResponseEntity<MedicationResponse> searchMedicationByBarcode(@PathVariable String barcode) {
        return ResponseEntity.status(HttpStatus.OK).body(medicationService.findMedicationByBarcode(barcode));
    }
}
