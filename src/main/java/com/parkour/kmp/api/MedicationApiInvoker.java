package com.parkour.kmp.api;

import com.parkour.kmp.api.exception.InvalidRequestException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class MedicationApiInvoker {

    private static final String apiKey = System.getenv("KMP_APP_APIKEY");
    private static final String url = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/";

    private HttpMethod method = HttpMethod.GET;
    private String path = "getDrbEasyDrugList";
    private String query;

    public MedicationApiInvoker(ApiSearchRequest request) {
        path = url + apiKey;
        query = request.getQuery();
    }

    public ResponseEntity<String> medicationSearchApi() throws InvalidRequestException {
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .path(path)
                .queryParam("serviceKey", apiKey)
                .queryParam("itemSeq", query)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> requestEntity = buildRequest(method, uri).build();
        return restTemplate.exchange(requestEntity, String.class);
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
