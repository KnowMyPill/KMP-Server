package com.parkour.kmp.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MedCodeApiResponse {
    @JsonProperty("한글상품명")
    private String itemName;

    @JsonProperty("품목기준코드")
    private String itemSeq;

    @JsonProperty("대표코드")
    private String representativeCode;

    @JsonProperty("표준코드")
    private String standardCode;

    @JsonProperty("제품코드(개정후)")
    private String productCode;

    @JsonProperty("일반명코드(성분명코드)")
    private String genericNameCode;

    @JsonProperty("ATC코드")
    private String atcCode;
}