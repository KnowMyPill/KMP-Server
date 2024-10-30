package com.parkour.kmp.api.user.controller;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.user.payload.request.UserSignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserApi {
    @PostMapping
    ResponseEntity<ApiResponse> createUser(@RequestBody UserSignUpRequest request);
}
