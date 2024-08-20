package com.parkour.kmp.api.invoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.domain.MedicationApiInvokerCommand;
import com.parkour.kmp.api.exception.InvalidRequestException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.parkour.kmp.api.payload.response.MedCodeApiResponse;
import com.parkour.kmp.api.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.payload.response.MedicationApiResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class MedicationApiInvoker {

    private static final String apiKey = System.getenv("KMP_APP_APIKEY");

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String url, path;
    private final HttpMethod method;

    public MedicationApiInvoker(RestTemplate restTemplate, ObjectMapper mapper, MedicationApiInvokerCommand command) {
        this.restTemplate = restTemplate;
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
            URI uri = UriComponentsBuilder.fromHttpUrl(url)
                    .path(path)
                    .queryParam("serviceKey", apiKey)
                    .queryParam("page", currentPage)
                    .queryParam("perPage", pageSize)
                    .encode()
                    .build()
                    .toUri();

            RequestEntity<Void> requestEntity = buildRequest(method, uri).build();
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                throw new InvalidRequestException("Failed to fetch med code data: " + responseEntity.getBody());
            }

            String responseBody = responseEntity.getBody();
            try {

                Map<String, Object> responseMap = mapper.readValue(responseBody, Map.class);
                List<Map<String, Object>> data = (List<Map<String, Object>>) responseMap.get("data");

                for (Map<String, Object> itemMap : data) {
                    MedCodeApiResponse item = mapper.convertValue(itemMap, MedCodeApiResponse.class);
                    responses.add(item);
                }

                if (data.size() < pageSize) {
                    break;
                }
                currentPage++;
            } catch (JsonProcessingException e) {
                throw new InvalidRequestException("Error processing JSON response: " + e);
            }
        }
        return new MedCodeSummaryResponse(pageSize, responses.size(), responses.size(), responses);
    }

    public MedicationApiResponse fetchMedicationData(String query) throws InvalidRequestException, JsonProcessingException {
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .path(path)
                .queryParam("serviceKey", apiKey)
                .queryParam("itemSeq", query)
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = buildRequest(method, uri).build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new InvalidRequestException("Failed to fetch medication data: " + responseEntity.getBody());
        }
        MedicationApiResponse response =  mapper.readValue(responseEntity.getBody(), MedicationApiResponse.class);
        System.out.println(response.toString()); // TODO: remove in production
        return response;
    }

    private RequestEntity.HeadersBuilder<?> buildRequest(HttpMethod method, URI uri) throws InvalidRequestException {
        if (method.equals(HttpMethod.GET)) {
            return RequestEntity.get(uri);
        } else if (method.equals(HttpMethod.POST)) {
            return RequestEntity.post(uri);
        } else {
            throw new InvalidRequestException("Invalid HTTP method: " + method);
        }
    }
}
