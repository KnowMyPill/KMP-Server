package com.parkour.kmp.api.history.service.impl;

import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.history.repository.HistoryRepository;
import com.parkour.kmp.api.history.service.HistoryService;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.user.domain.User;
import lombok.RequiredArgsConstructor;
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

    @Override
    @Transactional
    public void storeHistory(User user, Medication medication) {
        historyRepository.save(new History(user, medication));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<History> findHistoriesByUser(User user, int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        return historyRepository.findAllByUser(user.getId(), pageable);
    }


}
