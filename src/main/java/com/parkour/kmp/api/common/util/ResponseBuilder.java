package com.parkour.kmp.api.common.util;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.common.payload.impl.GenericApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static <T> ResponseEntity<ApiResponse> buildResponse(T data, boolean success, String message, HttpStatus status) {
        ApiResponse response = GenericApiResponse.builder()
                .success(success)
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(response, status);
    }
}
