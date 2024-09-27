package com.parkour.kmp.api.client.factory;

import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.invoker.ApiInvoker;

public interface ApiInvokerFactory {
    ApiInvoker getApiInvoker(ApiInvokerCmd cmd);
}
