package com.parkour.kmp.api.user.payload.request;

import jakarta.validation.constraints.NotNull;

public record UserSignUpRequest(@NotNull String token) {}
