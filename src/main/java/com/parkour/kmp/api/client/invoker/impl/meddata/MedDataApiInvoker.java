package com.parkour.kmp.api.client.invoker.impl.meddata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.ApiInvokerCmd;

import com.parkour.kmp.api.client.invoker.ApiInvoker;
import com.parkour.kmp.api.client.payload.response.meddata.MedItemApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class MedDataApiInvoker implements ApiInvoker {

    private static final String apiKey = System.getenv("KMP_APP_API_KEY");
    private static final String url = ApiInvokerCmd.GET_MED_FROM_CODE.getUrl();
    private static final String path = ApiInvokerCmd.GET_MED_FROM_CODE.getPath();
    private final WebClient client = WebClient.builder().build();


    @Override
    public MedItemApiResponse fetchMedicationData(String query) {

        URI uri = URI.create(url + path + "?serviceKey=" + apiKey + "&itemSeq=" + query + "&type=json");

        String result = client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        MedItemApiResponse response = null;
        try {
            Map<String, Object> responseMap = mapper.readValue(result, new TypeReference<Map<String, Object>>() {});

            // Extract 'body' as a Map
            Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");

            // Extract 'items' as a List of Maps
            List<Map<String, Object>> items = (List<Map<String, Object>>) bodyMap.get("items");

            if (!items.isEmpty()) {
                // Convert the first item to MedItemApiResponse
                response = mapper.convertValue(items.get(0), MedItemApiResponse.class);
            }
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }


}
