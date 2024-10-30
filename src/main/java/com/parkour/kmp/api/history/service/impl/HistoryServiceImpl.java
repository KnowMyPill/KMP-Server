package com.parkour.kmp.api.history.service.impl;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.history.domain.History;
import com.parkour.kmp.api.history.payload.request.HistoryStoreRequest;
import com.parkour.kmp.api.history.payload.response.HistoryResponse;
import com.parkour.kmp.api.history.repository.HistoryRepository;
import com.parkour.kmp.api.history.service.HistoryService;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.medication.payload.response.MedicationResponse;
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

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

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
    public int storeHistory(HistoryStoreRequest request) throws InvalidRequestException {
        User user = userService.findUserByToken(request.token());
        MedicationResponse response = medicationService.findMedicationByItemSeq(request.itemSeq());
        if (response == null) {
            throw new IllegalArgumentException("Medication not found.");
        }

        LocalDate expiryDate = processDate(request.date());

        Medication medication = medicationService.existsByItemSeq(request.itemSeq()) ?
                medicationService.retrieveMedicationByItemSeq(request.itemSeq()) :
                saveNewMedication(response);

        historyRepository.save(new History(user, medication, expiryDate));
        return 1;
    }


    @Override
    @Transactional(readOnly = true)
    public List<HistoryResponse> findHistoriesByUser(String token, int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        Page<History> histories = historyRepository.findAllByUser(token, pageable);

        List<HistoryResponse> responses = new ArrayList<>();
        for (History history : histories) {
            responses.add(mapper.map(history, HistoryResponse.class));
        }
        return responses;
    }

    private LocalDate processDate(String date) {
        return LocalDate.parse(date);
    }

    private Medication saveNewMedication(MedicationResponse response) {
        Medication medication = mapper.map(response, Medication.class);
        return medicationService.saveMedication(medication);
    }

}
