package com.parkour.kmp.api.medcode.service.impl;

import com.parkour.kmp.api.client.exception.InvalidRequestException;
import com.parkour.kmp.api.client.invoker.impl.MedCodeInvoker;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import com.parkour.kmp.api.medcode.domain.MedCode;
import com.parkour.kmp.api.medcode.repository.MedCodeRepository;
import com.parkour.kmp.api.medcode.service.MedCodeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedCodeServiceImpl implements MedCodeService {

    private final MedCodeRepository medCodeRepository;
    private final MedCodeInvoker invoker;

    private static final Logger logger = LoggerFactory.getLogger(MedCodeServiceImpl.class);


    @Override
    public void fetchAndStoreMedCodes() throws InvalidRequestException {
        logger.info("Starting the process to fetch and save medication codes.");

        invoker.fetchAllMedCodeData()
                .buffer(100)
                .flatMap(this::saveMedCodesBatch)
                .doOnComplete(() -> logger.info("Completed the process to fetch and save medication codes."))
                .doOnError(error -> logger.error("Error occurred while fetching or saving medication codes: ", error))
                .subscribe();

        logger.info("Completed the process to fetch and save medication codes.");
    }

    private Mono<Void> saveMedCodesBatch(List<MedCodeApiResponse> medCodeApiResponses) {
        return Mono.fromRunnable(() -> {
            List<MedCode> medCodes = medCodeApiResponses.stream()
                    .map(response -> new MedCode(
                            response.getItemSeq(),
                            response.getRepresentativeCode(),
                            response.getStandardCode(),
                            response.getProductCode()))
                    .collect(Collectors.toList());

            logger.info("Saving {} medCodes.", medCodes.size());
            medCodeRepository.saveAll(medCodes);
        });
    }
}
