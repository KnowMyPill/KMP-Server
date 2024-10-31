package com.parkour.kmp.api.fcm.controller;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.fcm.payload.request.MobileRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;

public interface FcmApi {
    @PostMapping("/send")
    ResponseEntity<ApiResponse> pushMessage(MobileRequest request) throws IOException;
}
