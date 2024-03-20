package com.mustafa.service;

import com.mustafa.dto.request.RegisterRequestDto;
import com.mustafa.dto.response.RegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void sendMail(String email,String activationCode) {
        String activationLink = generateActivationLink(email, activationCode);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("musoyuer@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("Hesabınızı Aktivasyonu ");
        mailMessage.setText("Hesabınızı aktive etmek için lütfen aşağıdaki linke tıklayınız: " + activationLink);
        javaMailSender.send(mailMessage);
    }

    public String generateActivationLink(String email, String activationCode) {
        return "http://34.163.132.23:8080/dev/v1/auth/activateStatus?email=" + email + "&activationCode=" + activationCode;
//        return "http://localhost:8080/swagger-ui/index.html#/auth-controller/activateStatus?email=" + email + "&activationCode=" + activationCode;
    }
}
