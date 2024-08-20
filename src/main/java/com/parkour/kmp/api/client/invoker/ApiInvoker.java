package com.parkour.kmp.api.client.invoker;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.exception.UnsupportedInvocationException;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeSummaryResponse;
import com.parkour.kmp.api.client.payload.response.meddata.MedItemApiResponse;

public interface ApiInvoker {
    default MedCodeSummaryResponse fetchAllMedCodeData() throws InvalidRequestException, UnsupportedInvocationException {
        throw new UnsupportedInvocationException("Not supported");
    }

    default MedItemApiResponse fetchMedicationData(String query) throws UnsupportedInvocationException {
        throw new UnsupportedInvocationException("Not supported");
    }
}
