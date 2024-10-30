package com.parkour.kmp.api.notification.controller.impl;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.common.payload.impl.ApiResponseDto;
import com.parkour.kmp.api.notification.controller.NotificationController;
import com.parkour.kmp.api.notification.payload.request.MobileRequest;
import com.parkour.kmp.api.notification.payload.request.impl.FcmMobileRequest;
import com.parkour.kmp.api.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fcm")
public class FcmController implements NotificationController {

    private final NotificationService service;

    @Override
    @PostMapping("/send")
    public ResponseEntity<ApiResponse> pushMessage(MobileRequest request) throws IOException {

        log.debug("[KMP Log] Sending push message. ");
        int result = service.sendMessage(request);
        ApiResponse response = ApiResponseDto.builder()
                .success(result == 1)
                .message("Message is successfully sent.")
                .build();
    }
}
