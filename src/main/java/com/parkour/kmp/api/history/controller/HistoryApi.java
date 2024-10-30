package com.parkour.kmp.api.history.controller;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface HistoryApi {

    @GetMapping("/my")
    ResponseEntity<ApiResponse> getMyHistory(@RequestParam String token,
                                                    @RequestParam(defaultValue = "0") int page);

    @PostMapping("/store")
    ResponseEntity<ApiResponse> storeHistory(@Validated @RequestBody HistoryStoreRequest request);
}