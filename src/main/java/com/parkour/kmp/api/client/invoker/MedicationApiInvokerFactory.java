package com.parkour.kmp.api.client.invoker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.MedicationApiInvokerCommand;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MedicationApiInvokerFactory {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MedicationApiInvokerFactory(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public MedicationApiInvoker createMedicationApiInvoker(MedicationApiInvokerCommand command) {
        return new MedicationApiInvoker(restTemplate, objectMapper, command);
    }

}
