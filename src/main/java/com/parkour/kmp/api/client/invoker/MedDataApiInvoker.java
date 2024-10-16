package com.parkour.kmp.api.client.invoker;

import com.parkour.kmp.api.client.payload.response.meddata.MedItemApiResponse;

public interface MedDataApiInvoker {
    MedItemApiResponse fetchMedicationData(String query);
}
