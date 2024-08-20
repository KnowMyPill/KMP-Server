package com.parkour.kmp.api.client.invoker;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.client.payload.response.MedDataApiResponse;

public interface ApiInvoker {
    default MedCodeSummaryResponse fetchAllMedCodeData() throws InvalidRequestException {
        throw new UnsupportedOperationException("Not supported");
    }

    default MedDataApiResponse fetchMedicationData(String query) {
        throw new UnsupportedOperationException("Not supported");
    }
}
