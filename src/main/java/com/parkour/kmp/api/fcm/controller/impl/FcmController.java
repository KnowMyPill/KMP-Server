package com.parkour.kmp.api.fcm.controller.impl;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.common.util.ResponseBuilder;
import com.parkour.kmp.api.fcm.payload.request.MobileRequest;
import com.parkour.kmp.api.fcm.service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fcm")
public class FcmController implements com.parkour.kmp.api.notification.controller.FcmApi {

    private final FcmService service;

    @Override
    public ResponseEntity<ApiResponse> pushMessage(MobileRequest request) throws IOException {

        log.debug("[KMP Log] Sending push message. ");
        int result = 0;
        try {
            result = service.sendMessage(request);
        } catch (Exception e) {
            log.error("[KMP Log] Error sending push message. ", e);
        }
        return ResponseBuilder.buildResponse(result, result == 1, "Push message sent successfully.", HttpStatus.CREATED);
    }
}
