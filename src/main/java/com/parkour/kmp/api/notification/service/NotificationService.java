package com.parkour.kmp.api.notification.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.parkour.kmp.api.notification.payload.request.MobileRequest;

public interface NotificationService {
    int sendMessage(MobileRequest request) throws FirebaseMessagingException;
}
