package com.parkour.kmp.api.client.invoker.meddata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.exception.UnsupportedInvocationException;
import com.parkour.kmp.api.client.factory.ApiInvokerFactory;
import com.parkour.kmp.api.client.invoker.impl.meddata.MedDataApiInvoker;
import com.parkour.kmp.api.client.payload.response.meddata.MedItemApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MedDataApiInvokerTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private ApiInvokerFactory apiInvokerFactory;

    private MedDataApiInvoker medDataApiInvoker;

    @Mock
    private WebClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(apiInvokerFactory.getApiInvoker(ApiInvokerCmd.GET_MED_FROM_CODE)).thenReturn(medDataApiInvoker);
        medDataApiInvoker = new MedDataApiInvoker();
    }

    @Test
    void testFetchMedicationDataSuccess() throws Exception {
        MedItemApiResponse actualResponse = medDataApiInvoker.fetchMedicationData("200003092");
        assertEquals("한미아스피린장용정100밀리그램", actualResponse.getItemName());
    }

    @Test
    void testFetchMedCodeFailure() throws Exception {
        assertThrows(UnsupportedInvocationException.class, () -> medDataApiInvoker.fetchAllMedCodeData());
    }
}
