package com.parkour.kmp.api.batch.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.factory.ApiInvokerFactory;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class BatchScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(BatchScheduler.class);

    private final JobLauncher jobLauncher;
    private final Job importMedCodesJob;
    private final ApiInvokerFactory apiInvokerFactory;
    private final DataSource dataSource;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void runJob() {
        try {
            LOG.info("Starting the batch job...");

            MedCodeSummaryResponse response = apiInvokerFactory.getApiInvoker(ApiInvokerCmd.GET_CODE_FROM_BARCODE).fetchAllMedCodeData();
            List<MedCodeApiResponse> medCodes = response.getData();

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(medCodes);
            Files.write(Paths.get("src/main/resources/sample-data.json"), jsonData.getBytes());

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update("TRUNCATE TABLE med_codes");

            // Run the batch job
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("testParameter", "testValue")
                    .toJobParameters();
            jobLauncher.run(importMedCodesJob, jobParameters);

            LOG.info("Batch job completed.");
        } catch (IOException e) {
            LOG.error("Failed to write JSON data to file", e);
        } catch (Exception e) {
            LOG.error("Failed to run the batch job", e);
        }
    }
}
