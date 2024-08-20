package com.parkour.kmp.api.medication.payload.response;

public record MedicationResponse(String itemName, String descEffect, String descUseMethod, String descWarningBeforeUsage, String descWarning, String descBannedCombo, String descSideEffect, String descManageMethod) {
}
