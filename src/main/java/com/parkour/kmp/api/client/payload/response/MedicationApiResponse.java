package com.parkour.kmp.api.client.payload.response;

import lombok.Getter;

@Getter
public class MedicationApiResponse {

    private String itemName;
    private String descEffect;
    private String descUseMethod;
    private String descWarningBeforeUsage;
    private String descWarning;
    private String descBannedCombo;
    private String descSideEffect;
    private String descManageMethod;

    public MedicationApiResponse(String itemName, String descEffect, String descUseMethod, String descWarningBeforeUsage, String descWarning, String descBannedCombo, String descSideEffect, String descManageMethod) {
        this.itemName = itemName;
        this.descEffect = descEffect;
        this.descUseMethod = descUseMethod;
        this.descWarningBeforeUsage = descWarningBeforeUsage;
        this.descWarning = descWarning;
        this.descBannedCombo = descBannedCombo;
        this.descSideEffect = descSideEffect;
        this.descManageMethod = descManageMethod;
    }

}
