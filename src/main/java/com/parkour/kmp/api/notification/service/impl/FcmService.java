package com.parkour.kmp.api.notification.service.impl;

import com.parkour.kmp.api.notification.payload.request.MobileRequest;
import com.parkour.kmp.api.notification.payload.request.impl.FcmMobileRequest;
import com.parkour.kmp.api.notification.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class FcmService implements NotificationService {

    @Override
    public int sendMessage(MobileRequest request) {
        FcmMobileRequest fcmRequest = (FcmMobileRequest) request;

        return 0;
    }
}
