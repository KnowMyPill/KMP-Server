package com.parkour.kmp.api.client.invoker.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.invoker.ApiInvoker;
import com.parkour.kmp.api.client.payload.response.MedCodeApiResponse;
import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AllMedCodeApiInvoker implements ApiInvoker {

    private final ObjectMapper mapper;
    private final WebClient client;

    private static final String apiKey = System.getenv("KMP_APP_API_KEY");
    private static final String url = ApiInvokerCmd.GET_CODE_FROM_BARCODE.getUrl();
    private static final String path = ApiInvokerCmd.GET_CODE_FROM_BARCODE.getPath();



    public MedCodeSummaryResponse fetchAllMedCodeData() throws InvalidRequestException {
        List<MedCodeApiResponse> responses = new ArrayList<>();
        int currentPage = 1;
        int pageSize = 100;

        while (true) {


            URI uri = URI.create(url + path + "?serviceKey=" + apiKey + "&page=" + currentPage + "&perPage=" + pageSize);

            String result = client.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println(result);


            try {
                Map<String, Object> responseMap = mapper.readValue(result, Map.class);
                System.out.println(responseMap);
                List<Map<String, Object>> data = (List<Map<String, Object>>) responseMap.get("data");

                if (data == null) {
                    throw new InvalidRequestException("No 'data' field in response.");
                }

                for (Map<String, Object> itemMap : data) {
                    MedCodeApiResponse item = mapper.convertValue(itemMap, MedCodeApiResponse.class);
                    responses.add(item);
                }

                if (data.size() < pageSize) {
                    break;
                }
                currentPage++;
            } catch (JsonProcessingException e) {
                throw new InvalidRequestException("Error processing JSON response: " + e.getMessage());
            }
        }
        return new MedCodeSummaryResponse(pageSize, responses.size(), responses.size(), responses);
    }
}
