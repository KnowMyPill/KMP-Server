package com.parkour.kmp.api.client.invoker;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import reactor.core.publisher.Flux;

public interface MedCodeApiInvoker {
    Flux<MedCodeApiResponse> fetchAllMedCodeData() throws InvalidRequestException;
}
