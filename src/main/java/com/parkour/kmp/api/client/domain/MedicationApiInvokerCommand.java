package com.parkour.kmp.api.client.domain;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum MedicationApiInvokerCommand {
    GET_MED_FROM_CODE("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService", "/getDrbEasyDrugList", HttpMethod.GET),
    GET_CODE_FROM_BARCODE("api.odcloud.kr/api", "/15067462/v1/uddi:4c72e98f-1bf9-470f-bee0-f451d54cd871", HttpMethod.GET);

    private final String url, path;
    private final HttpMethod method;

    MedicationApiInvokerCommand(String url, String path, HttpMethod method) {
        this.url = url;
        this.path = path;
        this.method = method;
    }
}
