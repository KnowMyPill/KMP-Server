package com.parkour.kmp.api.invoker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.domain.MedicationApiInvokerCommand;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MedicationApiInvokerTest {
    @Test
    void fetchMedicationData_success() throws Exception {
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        MedicationApiInvokerCommand mockProperties = mock(MedicationApiInvokerCommand.class);

        when(mockProperties.getUrl()).thenReturn("http://example.com");
        when(mockProperties.getPath()).thenReturn("/medications");

        // MedicationApiInvoker invoker = new MedicationApiInvoker(mockRestTemplate, mockMapper, mockProperties, HttpMethod.GET);

    }
}
