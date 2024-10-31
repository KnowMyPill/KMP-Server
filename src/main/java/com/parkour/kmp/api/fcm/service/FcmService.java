package com.parkour.kmp.api.fcm.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.parkour.kmp.api.notification.payload.request.MobileRequest;

public interface FcmService {
    int sendMessage(MobileRequest request) throws FirebaseMessagingException;
}
