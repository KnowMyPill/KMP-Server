package com.parkour.kmp.api.user.payload.request;

import com.parkour.kmp.api.user.validation.PasswordMatches;
import com.parkour.kmp.api.user.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatches
public record UserSignUpRequest(@NotBlank @Email @UniqueEmail String email, @NotBlank @Size(min = 6, message = "비밀번호는 6글자 이상이어야 합니다.") String password,
                            @NotBlank @Size(min = 6, message = "비밀번호는 6글자 이상이어야 합니다.") String checkPassword) {}
