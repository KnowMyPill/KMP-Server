package com.parkour.kmp.api.user.payload.request;

import com.parkour.kmp.api.user.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(@UniqueEmail @NotBlank @Email String email) {}