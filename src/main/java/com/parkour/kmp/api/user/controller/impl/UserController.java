package com.parkour.kmp.api.user.controller.impl;

import com.parkour.kmp.api.common.payload.ApiResponse;
import com.parkour.kmp.api.common.util.ResponseBuilder;
import com.parkour.kmp.api.user.controller.UserApi;
import com.parkour.kmp.api.user.domain.User;
import com.parkour.kmp.api.user.payload.request.UserSignUpRequest;
import com.parkour.kmp.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserSignUpRequest request) {
        User user = userService.signUp(request);
        return ResponseBuilder.buildResponse(user, true, "User created successfully.", HttpStatus.CREATED);
    }
}
