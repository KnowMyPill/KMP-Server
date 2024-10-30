package com.parkour.kmp.api.medication.controller;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.common.payload.impl.GenericApiResponse;
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
    public ResponseEntity<ApiResponse> searchMedicationByBarcode(@RequestParam String barcode) {
        ApiResponse response;
        try {
            MedicationResponse medResponse = medicationService.findMedicationByBarcode(barcode);
            response = GenericApiResponse.builder()
                .success(true)
                .data(medResponse)
                .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response = GenericApiResponse.builder()
                .success(false)
                .message(e.getMessage())
                .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response = GenericApiResponse.builder()
                .success(false)
                .message("An error occurred while fetching medication data.")
                .build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO: FOR TEST
    @GetMapping("/find")
    public ResponseEntity<MedicationResponse> searchMedicationById(@RequestParam String itemSeq) {
        return ResponseEntity.status(HttpStatus.OK).body(medicationService.findMedicationByItemSeq(itemSeq));
    }
}
