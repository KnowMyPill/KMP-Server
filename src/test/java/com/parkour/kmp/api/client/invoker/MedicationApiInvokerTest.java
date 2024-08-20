package com.parkour.kmp.api.client.invoker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.MedicationApiInvokerCommand;
import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.payload.response.MedCodeApiResponse;
import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.client.payload.response.MedicationApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MedicationApiInvokerTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper mapper;

    private MedicationApiInvoker medicationApiInvoker;

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
        medicationApiInvoker = new MedicationApiInvoker(mapper, MedicationApiInvokerCommand.GET_MED_FROM_CODE);
        MedicationApiResponse actualResponse = medicationApiInvoker.fetchMedicationData("200003092");
        System.out.println(actualResponse.getItemName());
        assertEquals("한미아스피린장용정100밀리그램", actualResponse.getItemName());
    }
}
