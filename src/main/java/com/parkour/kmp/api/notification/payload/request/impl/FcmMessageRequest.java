package com.parkour.kmp.api.notification.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Request sent to FCM
 */

@Getter
@Builder
public class FcmMessageRequest {
    private boolean validateOnly;
    private FcmMessageRequest.Message message;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Message {
        private FcmMessageRequest.Notification notification;
        private String token;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Notification {
        private String title;
        private String body;
        private String image;
    }
}
