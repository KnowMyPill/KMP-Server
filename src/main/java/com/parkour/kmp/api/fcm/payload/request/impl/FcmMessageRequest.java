package com.parkour.kmp.api.fcm.payload.request.impl;

import com.parkour.kmp.api.fcm.payload.request.MessageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Request sent to FCM
 */

@Builder
public class FcmMessageRequest implements MessageRequest {
    private boolean validateOnly;
    private FcmMessageRequest.Message message;

    @Override
    public boolean isValidateOnly() {
        return validateOnly;
    }

    @Override
    public Message getMessage() {
        return message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Message implements MessageRequest.Message {
        private FcmMessageRequest.Notification notification;
        private String token;

        @Override
        public Notification getNotification() {
            return notification;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Notification implements MessageRequest.Notification {
        private String title;
        private String body;
        private String image;

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getBody() {
            return body;
        }

        @Override
        public String getImage() {
            return image;
        }
    }
}
