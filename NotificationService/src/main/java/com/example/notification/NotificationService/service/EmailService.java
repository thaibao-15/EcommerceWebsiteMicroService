package com.example.notification.NotificationService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.notification.NotificationService.dto.request.EmailRequest;
import com.example.notification.NotificationService.dto.request.SendEmailRequest;
import com.example.notification.NotificationService.dto.request.Sender;
import com.example.notification.NotificationService.dto.response.EmailResponse;
import com.example.notification.NotificationService.exception.AppException;
import com.example.notification.NotificationService.exception.ErrorCode;
import com.example.notification.NotificationService.repository.EmailClient;

import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;

    @Value("${notification.email.brevo-apikey}")
    @NonFinal
    String apiKey;

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("Tổng tài đẹp trai")
                        .email("phambao15305@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
