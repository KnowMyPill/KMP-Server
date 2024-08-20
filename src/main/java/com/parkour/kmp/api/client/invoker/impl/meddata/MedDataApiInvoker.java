package com.parkour.kmp.api.client.invoker.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.ApiInvokerCmd;

import com.parkour.kmp.api.client.invoker.ApiInvoker;
import com.parkour.kmp.api.client.payload.response.MedDataApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class MedApiInvoker implements ApiInvoker {

    private static final String apiKey = System.getenv("KMP_APP_API_KEY");
    private static final String url = ApiInvokerCmd.GET_MED_FROM_CODE.getUrl();
    private static final String path = ApiInvokerCmd.GET_MED_FROM_CODE.getPath();

    private final ObjectMapper mapper;
    private final WebClient client;


    public MedDataApiResponse fetchMedicationData(String query) {

        URI uri = URI.create(url + path + "?serviceKey=" + apiKey + "&itemSeq=" + query + "&type=json");

        String result = client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(result);
        MedDataApiResponse response = null;
        try {
            Map<String, Object> resultMap = mapper.readValue(result, Map.class);

            Map<String, Object> body = (Map<String, Object>) resultMap.get("body");
            List<Map<String, Object>> items = (List<Map<String, Object>>) body.get("items");
            Map<String, Object> itemData = items.get(0);
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

    public MedDataApiResponse convertToDTO(Map<String, Object> data) {
        MedDataApiResponse dto = new MedDataApiResponse(
                (String) data.get("itemName"),
                (String) data.get("descEffect"),
                (String) data.get("descUseMethod"),
                (String) data.get("descWarningBeforeUsage"),
                (String) data.get("descWarning"),
                (String) data.get("descBannedCombo"),
                (String) data.get("descSideEffect"),
                (String) data.get("descManageMethod")
        );
        return dto;
    }


}
