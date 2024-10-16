package com.parkour.kmp.api.medcode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JsonMedCode {

    @JsonProperty("품목기준코드")
    private String itemSeq;

    @JsonProperty("대표코드")
    private String representativeCode;

    @JsonProperty("표준코드")
    private String standardCode;

    @JsonProperty("제품코드(개정후)")
    private String productCode;

}