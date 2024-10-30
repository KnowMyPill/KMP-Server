package com.parkour.kmp.api.notification.payload.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Request sent from Mobile, received at Server
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FcmMobileRequest {
    private String title;
    private String body;
    private String token;

    @Builder(toBuilder = true)
    public FcmMobileRequest(String title, String body, String token) {
        this.title = title;
        this.body = body;
        this.token = token;
    }
}
