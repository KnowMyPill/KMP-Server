package com.parkour.kmp.api.history.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import com.parkour.kmp.api.history.repository.HistoryRepository;
import com.parkour.kmp.api.history.service.HistoryService;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.medication.repository.MedicationRepository;
import com.parkour.kmp.api.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HistoryController.class)
public class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;
    @Mock
    private MedicationRepository medicationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("유효하지 않은 요청에 대해 히스토리 생성 시도")
    public void createHistory_shouldCauseError() throws Exception {

        HistoryStoreRequest request = new HistoryStoreRequest("invalid-token", "1234", "2024-10-28");

        doThrow(new IllegalArgumentException("User not found with given token."))
                .when(historyService).storeHistory(request);

        mockMvc.perform(post("/api/history/store")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("유효한 요청에 대해 히스토리 생성 시도")
    public void createHistory_shouldSucceed() throws Exception {

        HistoryStoreRequest request = new HistoryStoreRequest("valid-token", "1234", "2024-10-28");

        doNothing().when(historyService).storeHistory(request);

        mockMvc.perform(post("/api/history/store")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());

    }
}
