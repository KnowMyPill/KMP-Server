package com.parkour.kmp.medication.controller;

import com.parkour.kmp.medication.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/medication")
@RequiredArgsConstructor
public class MedicationController {
    private final MedicationService medicationService;
}
