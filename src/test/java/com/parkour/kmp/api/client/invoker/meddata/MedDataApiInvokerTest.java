package com.parkour.kmp.api.client.invoker.meddata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.invoker.impl.MedDataInvoker;
import com.parkour.kmp.api.client.payload.response.meddata.MedItemApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class MedDataApiInvokerTest {

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private MedDataInvoker medDataApiInvoker;

    @Mock
    private WebClient client;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(client.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(URI.class))).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);

    }


    /* Test commented out due to actual invocation for api
    @Test
    void testFetchMedicationDataSuccess() throws Exception {
        // Arrange
        String itemId = "200003092";
        String jsonResponse = "{\"itemName\": \"한미아스피린장용정100밀리그램\"}";

        // Mock the behavior of WebClient
        when(client.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(String.class))).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(jsonResponse));

        MedItemApiResponse expectedResponse = new MedItemApiResponse("한미아스피린장용정100밀리그램", "","", "", "", "", "", ""); // Add other required fields as necessary

        when(mapper.readValue(jsonResponse, MedItemApiResponse.class)).thenReturn(expectedResponse);

        // Act
        MedItemApiResponse actualResponse = medDataApiInvoker.fetchMedicationData(itemId);

        // Assert
        assertEquals("한미아스피린장용정100밀리그램", actualResponse.getItemName());
        System.out.println(actualResponse.getItemName());
    }
    */
}
