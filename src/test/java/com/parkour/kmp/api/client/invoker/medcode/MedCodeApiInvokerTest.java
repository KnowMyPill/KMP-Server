package com.parkour.kmp.api.client.invoker.medcode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.invoker.impl.MedCodeInvoker;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class MedCodeApiInvokerTest {

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private MedCodeInvoker invoker;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /** Test commented out due to excessive runtime
    @Test
    void fetchAllMedCodeData_shouldRetrieveRealData() throws InvalidRequestException {
        Flux<MedCodeApiResponse> result = invoker.fetchAllMedCodeData();

        result.subscribe(
                item -> {
                    assertNotNull(item);
                    System.out.println("Retrieved item: " + item);
                },
                error -> {
                    System.err.println("Error occurred: " + error.getMessage());
                },
                () -> {
                    System.out.println("Data retrieval complete.");
                }
        );
    }**/
}
