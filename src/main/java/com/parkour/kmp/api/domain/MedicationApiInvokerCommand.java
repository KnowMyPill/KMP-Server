package com.parkour.kmp.api.domain;

import lombok.Getter;

@Getter
public enum MedicationApiInvokerCommand {
    GET_MED_FROM_CODE("getDrbEasyDrugList");
    // TODO: 코드 맵핑 데이터 어떻게 가져올지

    private final String url;

    MedicationApiInvokerCommand(String url) {
        this.url = url;
    }
}
