package com.parkour.kmp.api.client.invoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.MedicationApiInvokerCommand;
import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.payload.response.ApiResponse;
import com.parkour.kmp.api.client.payload.response.MedCodeApiResponse;
import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.client.payload.response.MedicationApiResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
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


            URI uri = URI.create(url + path + "?serviceKey=" + apiKey + "&page=" + currentPage + "&perPage=" + pageSize + "&returnType=XML");

            WebClient client = WebClient.builder().baseUrl(url).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

            String result = client.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println(result);


            try {
                Map<String, Object> responseMap = mapper.readValue(result, Map.class);
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

    public MedicationApiResponse fetchMedicationData(String query) throws InvalidRequestException, URISyntaxException {


        URI uri = URI.create(url + path + "?serviceKey=" + apiKey + "&itemSeq=" + query);

        WebClient client = WebClient.builder().baseUrl(url).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

        String result = client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        MedicationApiResponse response = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(ApiResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Convert XML to ApiResponse object
            ApiResponse apiResponse = (ApiResponse) unmarshaller.unmarshal(new StringReader(result));

            // Check for null and process items
            if (apiResponse.getBody() != null && apiResponse.getBody().getItemsWrapper() != null) {
                List<MedicationApiResponse> medicationList = apiResponse.getBody().getItemsWrapper().getItem();
                response = medicationList.get(0);
            } else {
                System.out.println("No items found.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        /*try {
            return mapper.readValue(responseEntity.getBody(), MedicationApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new InvalidRequestException("Error processing JSON response: " + e.getMessage());
        }*/
        return response;
    }


}
