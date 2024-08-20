package com.parkour.kmp.api.payload.response;

import lombok.Getter;

import java.util.List;

@Getter
public class MedCodeResponse {

    private Long perPage;
    private Long totalCount;
    private Long currentCount;
    private List<MedCodeApiResponse> data;
}
