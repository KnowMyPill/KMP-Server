package com.parkour.kmp.api.client.factory.impl;

import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.factory.ApiInvokerFactory;
import com.parkour.kmp.api.client.invoker.ApiInvoker;
import com.parkour.kmp.api.client.invoker.impl.AllMedCodeApiInvoker;
import com.parkour.kmp.api.client.invoker.impl.MedApiInvoker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiInvokerFactoryImpl implements ApiInvokerFactory {

    private final AllMedCodeApiInvoker allMedCodeApiInvoker;
    private final MedApiInvoker medApiInvoker;

    @Override
    public ApiInvoker getApiInvoker(ApiInvokerCmd cmd) {
        return switch (cmd) {
            case GET_CODE_FROM_BARCODE -> allMedCodeApiInvoker;
            case GET_MED_FROM_CODE -> medApiInvoker;
        };
    }
}
