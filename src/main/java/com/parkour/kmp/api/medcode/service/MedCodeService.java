package com.parkour.kmp.api.medcode.service;

import com.parkour.kmp.api.client.exception.InvalidRequestException;

public interface MedCodeService {

    void fetchAndStoreMedCodes() throws InvalidRequestException;

}
