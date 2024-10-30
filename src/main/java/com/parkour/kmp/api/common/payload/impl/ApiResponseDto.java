package com.parkour.kmp.api.common.payload.impl;

import com.parkour.kmp.api.common.payload.ApiResponse;
import lombok.Builder;

@Builder
public class ApiResponseDto implements ApiResponse {
    boolean success;
    String message;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
