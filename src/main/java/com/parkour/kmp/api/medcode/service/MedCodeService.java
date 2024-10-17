package com.parkour.kmp.api.medcode.service;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.medcode.domain.MedCode;

public interface MedCodeService {

    void fetchAndStoreMedCodes() throws InvalidRequestException;
    MedCode findMedCodeByCodeStandard(String codeStandard) throws InvalidRequestException;

}
