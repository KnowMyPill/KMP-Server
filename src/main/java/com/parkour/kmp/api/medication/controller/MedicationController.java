package com.parkour.kmp.api.medication.controller;

import com.parkour.kmp.api.medication.payload.response.MedicationResponse;
import com.parkour.kmp.api.medication.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/medication")
@RequiredArgsConstructor
@RestController
public class MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    public ResponseEntity<Object> searchMedicationByBarcode(@RequestParam String barcode) {
        try {
            MedicationResponse response = medicationService.findMedicationByBarcode(barcode);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred."));
        }
    }

    // TODO: FOR TEST
    @GetMapping("/find")
    public ResponseEntity<MedicationResponse> searchMedicationById(@RequestParam String itemSeq) {
        return ResponseEntity.status(HttpStatus.OK).body(medicationService.findMedicationByItemSeq(itemSeq));
    }


}
