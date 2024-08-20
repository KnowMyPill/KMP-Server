package com.parkour.kmp.batch.tasks;

import com.parkour.kmp.api.domain.MedicationApiInvokerCommand;
import com.parkour.kmp.api.invoker.MedicationApiInvokerFactory;
import com.parkour.kmp.api.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.medication.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final MedicationApiInvokerFactory medicationApiInvokerFactory;
    private final MedCodeService medCodeService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void fetchAndUpdateMedCodeData() {
        try {
            MedCodeSummaryResponse medCodeSummaryResponse = medicationApiInvokerFactory.createMedicationApiInvoker(MedicationApiInvokerCommand.GET_CODE_FROM_BARCODE).fetchAllMedCodeData();
            medCodeService.updateMedCodeData(medCodeSummaryResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // send an api request and receive a json response once a month
    // parse the json response and make updates to the database if there are changes

}
