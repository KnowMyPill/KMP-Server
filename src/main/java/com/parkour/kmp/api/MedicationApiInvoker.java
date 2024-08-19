package com.parkour.kmp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.exception.InvalidRequestException;
import java.net.URI;

import com.parkour.kmp.medication.payload.response.MedicationApiResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MedicationApiInvoker {

    private static final String apiKey = System.getenv("KMP_APP_APIKEY");
    private static final String url = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    private HttpMethod method = HttpMethod.GET;
    private String path = "getDrbEasyDrugList";

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
