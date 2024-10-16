package com.parkour.kmp.api.user.service;

import com.parkour.kmp.api.user.domain.User;
import com.parkour.kmp.api.user.payload.request.UserSignUpRequest;
import com.parkour.kmp.api.user.payload.request.UserUpdateRequest;

public interface UserService {
    User signUp(UserSignUpRequest signUpRequest);
    User updateEmail(Long userId, UserUpdateRequest updateRequest);
    void deleteAccount(Long userId);
}
