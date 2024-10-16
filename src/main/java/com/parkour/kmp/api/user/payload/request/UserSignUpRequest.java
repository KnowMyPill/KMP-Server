package com.parkour.kmp.api.user.payload.request;

import com.parkour.kmp.api.user.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatches
public record UserSignUpRequest(String token) {}
