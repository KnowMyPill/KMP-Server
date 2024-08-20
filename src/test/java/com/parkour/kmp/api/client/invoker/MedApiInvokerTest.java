package com.parkour.kmp.api.client.invoker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.invoker.impl.MedApiInvoker;
import com.parkour.kmp.api.client.payload.response.MedDataApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MedApiInvokerTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper mapper;

    private MedApiInvoker medApiInvoker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    /*
    @Test
    void testFetchAllMedCodeData_Success() throws Exception {
        medicationApiInvoker = new MedicationApiInvoker(mapper, MedicationApiInvokerCommand.GET_CODE_FROM_BARCODE);

        MedCodeSummaryResponse result = medicationApiInvoker.fetchAllMedCodeData();

        assertNotNull(result);
        assertEquals(1, result.getTotalCount());
        //assertEquals(1, result.getCurrentCount());
        //assertEquals(1, result.getData().size());
    }

     */


    @Test
    void testFetchMedicationDataSuccess() throws Exception {
        MedDataApiResponse actualResponse = medApiInvoker.fetchMedicationData("200003092");
        System.out.println(actualResponse.getItemName());
        assertEquals("한미아스피린장용정100밀리그램", actualResponse.getItemName());
    }
}
