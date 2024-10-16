package com.parkour.kmp.api.medication.controller;

import com.parkour.kmp.api.medication.payload.response.MedicationResponse;
import com.parkour.kmp.api.medication.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/medication")
@RequiredArgsConstructor
@RestController
public class MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    public ResponseEntity<MedicationResponse> searchMedicationByBarcode(@PathVariable String barcode) {
        return ResponseEntity.status(HttpStatus.OK).body(medicationService.findMedicationByBarcode(barcode));
    }
    
    // TODO: FOR TEST
    @GetMapping("/find")
    public ResponseEntity<MedicationResponse> searchMedicationById(@RequestParam String itemSeq) {
        return ResponseEntity.status(HttpStatus.OK).body(medicationService.findMedicationByItemSeq(itemSeq));
    }
}
