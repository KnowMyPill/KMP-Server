package com.parkour.kmp.api.history.controller;

import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import com.parkour.kmp.api.history.payload.response.HistoryResponse;
import com.parkour.kmp.api.history.service.HistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/my")
    public ResponseEntity<List<HistoryResponse>> getMyHistory(@RequestParam String token,
                                                              @RequestParam(defaultValue = "0") int page) {
        List<HistoryResponse> histories = historyService.findHistoriesByUser(token, page);
        if (histories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(histories);
    }

    @PostMapping("/store")
    public ResponseEntity<Void> storeHistory(@Validated @RequestBody HistoryStoreRequest request) {
        try {
            historyService.storeHistory(request);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
