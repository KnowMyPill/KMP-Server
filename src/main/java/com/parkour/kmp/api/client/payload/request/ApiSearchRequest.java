package com.parkour.kmp.api.client.payload.request;

import com.parkour.kmp.api.client.domain.ApiInvokerCmd;
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
    ApiInvokerCmd command;

    protected ApiSearchRequest() {}

    public ApiSearchRequest(String query, ApiInvokerCmd command) {
        this.query = query;
        this.command = command;
    }
}
