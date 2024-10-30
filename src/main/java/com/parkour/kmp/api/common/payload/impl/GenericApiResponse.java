package com.parkour.kmp.api.common.payload.impl;

import com.parkour.kmp.api.common.payload.ApiResponse;
import lombok.Builder;

@Builder
public class GenericApiResponse<T> implements ApiResponse {
    private boolean success;
    private String message;
    private T data;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
