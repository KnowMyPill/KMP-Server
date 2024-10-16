package com.parkour.kmp.api.user.service.impl;

import com.parkour.kmp.api.user.domain.User;
import com.parkour.kmp.api.user.payload.request.UserSignUpRequest;
import com.parkour.kmp.api.user.payload.request.UserUpdateRequest;
import com.parkour.kmp.api.user.repository.UserRepository;
import com.parkour.kmp.api.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User signUp(UserSignUpRequest signUpRequest) {
        User user = createUser(signUpRequest.email(), signUpRequest.password());
        return userRepository.save(user);
    }



    @Override
    @Transactional
    public User updateEmail(Long userId, UserUpdateRequest updateRequest) {
        User user = findUserById(userId);
        user.updateEmail(updateRequest.email());
        return userRepository.save(user);
    }



    @Override
    public void deleteAccount(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    private User createUser(String email, String password) {
        return new User(email, password);
    }
}
