package com.parkour.kmp.api.client.payload.response.medcode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedCodeApiResponse {
    @JsonProperty("품목기준코드")
    private String itemSeq;

    @JsonProperty("대표코드")
    private String representativeCode;

    @JsonProperty("표준코드")
    private String standardCode;

    @JsonProperty("제품코드(개정후)")
    private String productCode;

    @JsonCreator
    public MedCodeApiResponse(
            @JsonProperty("품목기준코드") String itemSeq,
            @JsonProperty("대표코드") String representativeCode,
            @JsonProperty("표준코드") String standardCode,
            @JsonProperty("제품코드(개정후)") String productCode) {

        this.itemSeq = itemSeq;
        this.representativeCode = representativeCode;
        this.standardCode = standardCode;
        this.productCode = productCode;
    }
}
