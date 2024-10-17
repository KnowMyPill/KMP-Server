package com.parkour.kmp.api.batch.processor;

import com.parkour.kmp.api.medcode.domain.MedCode;
import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MedCodeProcessor implements ItemProcessor<MedCodeApiResponse, MedCode> {
    private static final Logger logger = LoggerFactory.getLogger(MedCodeProcessor.class);

    @Override
    public MedCode process(MedCodeApiResponse item) throws Exception {
        if (item == null) {
            logger.warn("Received null item in MedCodeProcessor");
            return null;
        }

        try {
            return new MedCode(
                    item.getItemSeq(),
                    item.getRepresentativeCode(),
                    item.getStandardCode()
            );
        } catch (Exception e) {
            logger.error("Error processing item: {}", item, e);
            throw e;
        }
    }

}


