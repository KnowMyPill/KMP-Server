package com.parkour.kmp.api.notification.service;

import com.parkour.kmp.api.notification.payload.request.MobileRequest;

public interface NotificationService {
    int sendMessage(MobileRequest request);
}
