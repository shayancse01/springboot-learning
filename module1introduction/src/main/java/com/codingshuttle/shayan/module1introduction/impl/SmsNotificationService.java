package com.codingshuttle.shayan.module1introduction.impl;

import com.codingshuttle.shayan.module1introduction.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Sms sending" + message);
    }
}
