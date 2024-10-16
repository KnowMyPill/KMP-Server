package com.parkour.kmp.api.history.controller;

import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import com.parkour.kmp.api.history.service.HistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/my")
    public ResponseEntity<List<History>> getMyHistory(@RequestParam String token,
                                                      @RequestParam(defaultValue = "0") int page) {
        Page<History> histories = historyService.findHistoriesByUser(token, page);
        if (histories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(histories.getContent());
    }

    @PostMapping("/store")
    public ResponseEntity<Void> storeHistory(@Validated @RequestBody HistoryStoreRequest request) {
        historyService.storeHistory(request);
        return ResponseEntity.ok().build();
    }
}
