package com.parkour.kmp.api.client.factory.impl;

import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.factory.ApiInvokerFactory;
import com.parkour.kmp.api.client.invoker.ApiInvoker;
import com.parkour.kmp.api.client.invoker.impl.medcode.AllMedCodeApiInvoker;
import com.parkour.kmp.api.client.invoker.impl.meddata.MedDataApiInvoker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiInvokerFactoryImpl implements ApiInvokerFactory {

    private final AllMedCodeApiInvoker allMedCodeApiInvoker;
    private final MedDataApiInvoker medDataApiInvoker;

    @Override
    public ApiInvoker getApiInvoker(ApiInvokerCmd cmd) {
        return switch (cmd) {
            case GET_CODE_FROM_BARCODE -> allMedCodeApiInvoker;
            case GET_MED_FROM_CODE -> medDataApiInvoker;
        };
    }
}
