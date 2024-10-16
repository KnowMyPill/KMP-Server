package com.parkour.kmp.api.user.service.impl;

import com.parkour.kmp.api.user.domain.User;
import com.parkour.kmp.api.user.payload.request.UserSignUpRequest;
import com.parkour.kmp.api.user.repository.UserRepository;
import com.parkour.kmp.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User signUp(UserSignUpRequest signUpRequest) {
        User user = new User(signUpRequest.token());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String token) {
        User user = findUserByToken(token);
        userRepository.delete(user);
    }
    private User findUserByToken(String token) {
        return userRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
}
