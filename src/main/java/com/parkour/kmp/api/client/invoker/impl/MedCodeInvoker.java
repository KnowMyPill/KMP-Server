package com.parkour.kmp.api.client.invoker.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.invoker.MedCodeApiInvoker;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class MedCodeInvoker implements MedCodeApiInvoker {

    private final WebClient client;

    private static final String apiKey = System.getenv("KMP_APP_API_KEY");
    private static final String url = ApiInvokerCmd.GET_CODE_FROM_BARCODE.getUrl();
    private static final String path = ApiInvokerCmd.GET_CODE_FROM_BARCODE.getPath();

    /*public MedCodeInvoker(WebClient.Builder clientBuilder) {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
                .build();
        this.client = clientBuilder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }*/

    @Override
    public Flux<MedCodeApiResponse> fetchAllMedCodeData() throws InvalidRequestException {

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
                .build();


        AtomicInteger currentPage = new AtomicInteger(1);
        int pageSize = 1000;
        ObjectMapper mapper = new ObjectMapper();

        return Flux.create(sink -> {
            while (true) {
                URI uri = URI.create(url + path + "?serviceKey=" + apiKey + "&page=" + currentPage.get() + "&perPage=" + pageSize);

                String result = client.get()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();


                try {
                    Map<String, Object> responseMap = mapper.readValue(result, Map.class);


                    List<Map<String, Object>> data = (List<Map<String, Object>>) responseMap.get("data");
                    if (data == null) {
                        throw new InvalidRequestException("No 'data' field in response.");
                    }

                    for (Map<String, Object> itemMap : data) {
                        MedCodeApiResponse item = mapper.convertValue(itemMap, MedCodeApiResponse.class);
                        sink.next(item);
                    }

                    if (data.size() < pageSize) {
                        sink.complete();
                        break;
                    }

                    currentPage.incrementAndGet();
                } catch (JsonProcessingException | InvalidRequestException e) {
                    sink.error(new InvalidRequestException("Error processing JSON response: " + e.getMessage()));
                    break;
                }
            }
        });
    }

}
