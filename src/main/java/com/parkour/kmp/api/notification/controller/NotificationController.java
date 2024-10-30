package com.parkour.kmp.api.notification.controller;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.notification.payload.request.MobileRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface NotificationController {
    ResponseEntity<ApiResponse> pushMessage(MobileRequest request) throws IOException;
}
