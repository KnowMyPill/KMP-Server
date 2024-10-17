package com.parkour.kmp.api.history.service;

import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import com.parkour.kmp.api.history.repository.HistoryRepository;
import com.parkour.kmp.api.history.service.impl.HistoryServiceImpl;
import com.parkour.kmp.api.medication.service.MedicationService;
import com.parkour.kmp.api.user.domain.User;
import com.parkour.kmp.api.user.repository.UserRepository;
import com.parkour.kmp.api.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceImplTest {

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MedicationService medicationService;

    @Mock
    private UserService userService;

    @InjectMocks
    private HistoryServiceImpl historyService;

    @Test
    @DisplayName("존재하지 않는 유저에 대한 히스토리 생성에 실패한다")
    public void storeHistory_shouldFailWithInvalidUser() {
        HistoryStoreRequest request = new HistoryStoreRequest("invalid-token", "200003092");
        assertThrows(IllegalArgumentException.class,
                ()->historyService.storeHistory(request));
    }

    @Test
    @DisplayName("존재하지 않는 약에 대한 히스토리 생성에 실패한다")
    public void storeHistory_shouldFailWithInvalidMed() {
        User user = new User("1234");
        userRepository.save(user);

        HistoryStoreRequest request = new HistoryStoreRequest("1234", "invalid-item");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                ()->historyService.storeHistory(request));
        assert(thrown.getMessage().startsWith("Med"));
    }


}
