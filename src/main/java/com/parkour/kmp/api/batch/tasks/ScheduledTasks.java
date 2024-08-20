package com.parkour.kmp.api.batch.tasks;

import com.parkour.kmp.api.client.domain.MedicationApiInvokerCommand;
import com.parkour.kmp.api.client.invoker.MedicationApiInvokerFactory;
import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.medcode.service.MedCodeService;
import com.parkour.kmp.api.medcode.service.impl.MedCodeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
