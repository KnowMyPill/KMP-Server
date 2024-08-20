package com.parkour.kmp.api.batch.tasks;

import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
import com.parkour.kmp.api.client.factory.ApiInvokerFactory;
import com.parkour.kmp.api.client.factory.impl.MedApiInvokerFactoryImpl;
import com.parkour.kmp.api.client.payload.response.MedCodeSummaryResponse;
import com.parkour.kmp.api.medcode.service.MedCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final MedCodeService medCodeService;
    private final ApiInvokerFactory apiInvokerFactory;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void fetchAndUpdateMedCodeData() {
        try {
            MedCodeSummaryResponse medCodeSummaryResponse = apiInvokerFactory.getApiInvoker(ApiInvokerCmd.GET_CODE_FROM_BARCODE).fetchAllMedCodeData();
            medCodeService.updateMedCodeData(medCodeSummaryResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // send an api request and receive a json response once a month
    // parse the json response and make updates to the database if there are changes

}
