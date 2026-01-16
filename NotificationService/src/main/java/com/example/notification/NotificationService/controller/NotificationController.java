package com.example.notification.NotificationService.controller;

import com.example.event.dto.NotificationEvent;
import com.example.notification.NotificationService.dto.request.Recipient;
import com.example.notification.NotificationService.dto.request.SendEmailRequest;
import com.example.notification.NotificationService.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationController {
    EmailService emailService;
    @KafkaListener(topics = "notification-delivery2")
    public void listenNotificationDelivery(NotificationEvent message){
        Recipient recipient = Recipient.builder()
                .name(message.getBody())
                .email(message.getRecipient())
                .build();
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .to(recipient)
                .subject(message.getSubject())
                .htmlContent(message.getBody())
                .build();
        emailService.sendEmail(sendEmailRequest);
    }
}
