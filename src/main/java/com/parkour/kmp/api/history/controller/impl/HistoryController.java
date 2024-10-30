package com.parkour.kmp.api.history.controller;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.common.payload.impl.GenericApiResponse;
import com.parkour.kmp.api.common.util.ResponseBuilder;
import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import com.parkour.kmp.api.history.payload.response.HistoryResponse;
import com.parkour.kmp.api.history.service.HistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/my")
    public ResponseEntity<ApiResponse> getMyHistory(@RequestParam String token,
                                                    @RequestParam(defaultValue = "0") int page) {
        List<HistoryResponse> histories = historyService.findHistoriesByUser(token, page);
        if (histories.isEmpty()) {
            return ResponseBuilder.buildResponse(null, false, "Histories not found.", HttpStatus.NOT_FOUND);
        }
        return ResponseBuilder.buildResponse(histories, true, "Histories fetched successfully.", HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<ApiResponse> storeHistory(@Validated @RequestBody HistoryStoreRequest request) {
        try {
            int result = historyService.storeHistory(request);
            return ResponseBuilder.buildResponse(result, true, "History stored successfully.", HttpStatus.CREATED);
        } catch (InvalidRequestException e) {
            return ResponseBuilder.buildResponse(null, false, e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
