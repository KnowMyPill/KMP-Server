package com.parkour.kmp.api.user.service;

import com.parkour.kmp.api.user.domain.User;
import com.parkour.kmp.api.user.payload.request.UserSignUpRequest;
import com.parkour.kmp.api.user.repository.UserRepository;
import com.parkour.kmp.api.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입 성공")
    void signUp_success() {
        UserSignUpRequest request = new UserSignUpRequest("1234");
        User user = new User("1234");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.signUp(request);

        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(savedUser);
        assertEquals("1234", savedUser.getToken());
    }

    @Test
    @DisplayName("회원 식별 성공")
    void findUserByToken_success() {
        String token = "5678";
        User user = new User(token);
        when(userRepository.findByToken(token)).thenReturn(Optional.of(user));

        User found = userService.findUserByToken(token);
        assertNotNull(found);
        assertEquals(token, found.getToken());
    }

    @Test
    @DisplayName("존재하지 않는 회원 식별 실패")
    void findUserByToken_fail() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> userService.findUserByToken("0000"));
        assertEquals(thrown.getMessage(), "User not found");

    }
}
