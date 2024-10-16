package com.parkour.kmp.api.user.service;

import com.parkour.kmp.api.user.domain.User;
import com.parkour.kmp.api.user.payload.request.UserSignUpRequest;

public interface UserService {
    User signUp(UserSignUpRequest signUpRequest);
    void deleteUser(String token);
}
