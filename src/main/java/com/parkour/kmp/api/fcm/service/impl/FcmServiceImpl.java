package com.parkour.kmp.api.fcm.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.parkour.kmp.api.fcm.service.FcmService;
import com.parkour.kmp.api.fcm.payload.request.MobileRequest;
import com.parkour.kmp.api.fcm.payload.request.impl.FcmMobileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmServiceImpl implements FcmService {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public int sendMessage(MobileRequest request) throws FirebaseMessagingException {
        FcmMobileRequest fcmRequest = (FcmMobileRequest) request;
        Message message = createMessage(fcmRequest);
        String response = firebaseMessaging.send(message);
        return response == null ? 0 : 1;
    }

    private Message createMessage(FcmMobileRequest request) {
        return Message.builder()
                .setToken(request.getToken())
                .putData("title", request.getTitle())
                .putData("body", request.getBody())
                .build();
    }
}
