package com.parkour.kmp.api.fcm.payload.request;

public interface MessageRequest {
    boolean isValidateOnly();
    Message getMessage();

    interface Message {
        Notification getNotification();
        String getToken();
    }

    interface Notification {
        String getTitle();
        String getBody();
        String getImage();
    }
}
