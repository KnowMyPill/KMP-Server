package com.parkour.kmp.api.medication.controller;

import com.parkour.kmp.api.common.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface MedicationApi {
    @GetMapping
    ResponseEntity<ApiResponse> searchMedicationByBarcode(@RequestParam String barcode);

    @GetMapping("/find")
    ResponseEntity<ApiResponse> searchMedicationById(@RequestParam String itemSeq);
}
