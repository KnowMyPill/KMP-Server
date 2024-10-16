package com.parkour.kmp.api.batch.job;

import com.parkour.kmp.api.medcode.service.MedCodeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MedCodeBatchJob {

    private final MedCodeService medCodeService;
    private static final Logger logger = LoggerFactory.getLogger(MedCodeBatchJob.class);

    @Scheduled(cron = "0 0 0 1 * *")
    public void fetchMedCodes() {
        logger.info("Starting the batch job to fetch and store MedCode data...");
        try {
            medCodeService.fetchAndStoreMedCodes();
            logger.info("Batch job completed successfully.");
        } catch (Exception e) {
            logger.error("An error occurred while fetching and storing MedCode data: {}", e.getMessage(), e);
        }
    }
}
