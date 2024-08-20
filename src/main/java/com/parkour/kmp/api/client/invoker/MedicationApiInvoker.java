package com.parkour.kmp.api.client.invoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.MedicationApiInvokerCommand;
import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.payload.response.MedCodeApiResponse;
import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.client.payload.response.MedicationApiResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MedicationApiInvoker {

    private static final String apiKey = System.getenv("KMP_APP_API_KEY");
    private final ObjectMapper mapper;

    private final String url;
    private final String path;
    private final HttpMethod method;

    public MedicationApiInvoker(ObjectMapper mapper, MedicationApiInvokerCommand command) {
        this.mapper = mapper;
        this.url = command.getUrl();
        this.path = command.getPath();
        this.method = command.getMethod();
    }

    public MedCodeSummaryResponse fetchAllMedCodeData() throws InvalidRequestException {
        List<MedCodeApiResponse> responses = new ArrayList<>();
        int currentPage = 1;
        int pageSize = 100;

        while (true) {


            URI uri = URI.create(url + path + "?serviceKey=" + apiKey + "&page=" + currentPage + "&perPage=" + pageSize);

            WebClient client = WebClient.builder().baseUrl(url).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

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

    public MedicationApiResponse fetchMedicationData(String query) {

        URI uri = URI.create(url + path + "?serviceKey=" + apiKey + "&itemSeq=" + query + "&type=json");

        WebClient client = WebClient.builder().baseUrl(url).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

        String result = client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(result);
        MedicationApiResponse response = null;
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

    public MedicationApiResponse convertToDTO(Map<String, Object> data) {
        MedicationApiResponse dto = new MedicationApiResponse(
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
