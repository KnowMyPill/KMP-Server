package com.parkour.kmp.api.client.invoker.medcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.exception.UnsupportedInvocationException;
import com.parkour.kmp.api.client.factory.ApiInvokerFactory;
import com.parkour.kmp.api.client.invoker.impl.medcode.AllMedCodeApiInvoker;
import com.parkour.kmp.api.client.payload.response.meddata.MedItemApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MedCodeApiInvokerTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private ApiInvokerFactory apiInvokerFactory;

    private AllMedCodeApiInvoker allMedCodeApiInvoker;

    @Mock
    private WebClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(apiInvokerFactory.getApiInvoker(ApiInvokerCmd.GET_CODE_FROM_BARCODE)).thenReturn(allMedCodeApiInvoker);
        allMedCodeApiInvoker = new AllMedCodeApiInvoker();
    }

    @Test
    void testFetchMedDataFailure() throws Exception {
        assertThrows(UnsupportedInvocationException.class, () -> allMedCodeApiInvoker.fetchMedicationData("200003092"));
    }

    /*
    @Test
    void testFetchAllMedCodeDataSuccess() throws Exception {
        MedCodeSummaryResponse actualResponse = allMedCodeApiInvoker.fetchAllMedCodeData();
    }
    */
}

