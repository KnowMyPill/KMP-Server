package com.parkour.kmp.api.payload.request;

import com.parkour.kmp.api.domain.MedicationApiInvokerCommand;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
public class ApiSearchRequest {
    @Length(min = 1, max = 50)
    private String query;

    @Enumerated(EnumType.STRING)
    MedicationApiInvokerCommand command;

    protected ApiSearchRequest() {}

    public ApiSearchRequest(String query, MedicationApiInvokerCommand command) {
        this.query = query;
        this.command = command;
    }
}
