package com.parkour.kmp.api.batch.processor;

import com.parkour.kmp.api.medcode.domain.MedCode;
import com.parkour.kmp.api.medcode.dto.JsonMedCode;
import org.springframework.batch.item.ItemProcessor;

import com.parkour.kmp.api.client.payload.response.medcode.MedCodeApiResponse;
import com.parkour.kmp.api.medcode.domain.MedCode;
import org.springframework.batch.item.ItemProcessor;

public class MedCodeProcessor implements ItemProcessor<MedCodeApiResponse, MedCode> {

    @Override
    public MedCode process(MedCodeApiResponse item) throws Exception {
        // Implement the transformation logic here
        return new MedCode(
                item.getItemSeq(),
                item.getRepresentativeCode(),
                item.getStandardCode(),
                item.getProductCode(),
                item.getAtcCode()
        );
    }
}


