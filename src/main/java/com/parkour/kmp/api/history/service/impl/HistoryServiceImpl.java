package com.parkour.kmp.api.history.service.impl;

import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import com.parkour.kmp.api.history.repository.HistoryRepository;
import com.parkour.kmp.api.history.service.HistoryService;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.medication.service.MedicationService;
import com.parkour.kmp.api.user.domain.User;
import com.parkour.kmp.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private static final int DEFAULT_PAGE_SIZE = 20;

    private final HistoryRepository historyRepository;
    private final UserService userService;
    private final MedicationService medicationService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public void storeHistory(HistoryStoreRequest request) {
        User user = userService.findUserByToken(request.token());
        Medication medication = mapper.map(medicationService.findMedicationByItemSeq(request.itemSeq()), Medication.class);
        historyRepository.save(new History(user, medication));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<History> findHistoriesByUser(String token, int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        return historyRepository.findAllByUser(token, pageable);
    }


}
